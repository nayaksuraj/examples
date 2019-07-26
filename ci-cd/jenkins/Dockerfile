FROM jenkins/jenkins:lts

# if we want to install via apt
USER root
RUN apt-get update && apt-get install -y python-pip cmake libboost-timer-dev
COPY conan-requirements.txt conan-requirements.txt
RUN pip install conan && pip install -r conan-requirements.txt
# drop back to the regular jenkins user - good practice
# Install Docker from Docker Inc. repositories.
RUN curl -sSL https://get.docker.com/ | sh
USER jenkins
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
COPY jenkins.yaml /usr/share/jenkins/ref/jenkins.yaml
ENV CASC_JENKINS_CONFIG=/usr/share/jenkins/ref/jenkins.yaml
