package com.springbootelkdemo;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController

class ELKDemoController {
    private static final Logger LOG = Logger.getLogger(ELKDemoController.class.getName());

    @Autowired
    RestTemplate restTemplete;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/elk-demo")
    public String helloWorld() {
        String response = "Welcome to Spring boot ELK demo : " + new Date();
        LOG.log(Level.INFO, response);

        return response;
    }

    @RequestMapping(value = "/elk-demo-password")
    public String elkPasswordMasking() {
        String response = "Password Masking Demo: pass123";
        LOG.log(Level.INFO, response);

        return response;
    }

    @RequestMapping(value = "/exception")
    public String exception() {
        String response = "";
        try {
            throw new Exception("Exception has occured....");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            LOG.error("Exception - " + stackTrace);
            response = stackTrace;
        }

        return response;
    }

    @RequestMapping(value = "/mb-deployment-log")
    public String mbDeploymentLog() {
        String response = " TimeStamp: 2019-08-30 13:50:57.771\n" +
                "AppId:     MB Deployment System\n" +
                "User:      IAF\n" +
                "Thread:    IAF Log Rotation Timer (Aug 30, 2019 1:49:57 PM) - MB Deployment System\n" +
                "Severity:  Trace\n" +
                "Category:  Rotation\n" +
                "TraceId:   -999999\n" +
                "Method:    n.a.\n" +
                "Message:   Auto file rotation trigger.\n";

        LOG.log(Level.INFO, response);

        return response;
    }

    @RequestMapping(value = "/mb-preformance-log")
    public String mbDeploymenPerformanceLog() {
        String response = "MB Deployment System\tTimer Thread\t18138\t30057765\tIAF Log Rotation Timer (Aug 30, 2019 1:49:57 PM) - MB Deployment System\tcom.deere.u90.iaf.logging.boundary.RotationTimer\trun()\tAuto file rotation trigger.";

        LOG.log(Level.INFO, response);

        return response;
    }

    @RequestMapping(value = "/mb-deployment-event-log")

    public String mbDeploymenEventLog() {
        String response = "TimeStamp: 2019-07-15 17:44:08.33\n" +
                "AppId:     IAF Deployment System\n" +
                "User:      IP8C7ZS\n" +
                "Thread:    WebContainer : 3\n" +
                "Severity:  Error\n" +
                "Category:  Deployment\n" +
                "MessageId: #0#\n" +
                "Message:   Application ErrorError determining if the Context Root has changed.\n" +
                "MachineName = llabwas6        \n" +
                "JvmName = iaf     \n" +
                "ApplicationName = IafFrameworks   \n" +
                "FileName = IafFrameworks_2018-11-21_13-23-56.ear\n" +
                "Context:   com.deere.u90.iaf.deployment.exceptions.ApplicationException: Error determining if the Context Root has changed.\n" +
                "MachineName = llabwas6        \n" +
                "JvmName = iaf     \n" +
                "ApplicationName = IafFrameworks   \n" +
                "FileName = IafFrameworks_2018-11-21_13-23-56.ear\n" +
                "timestamp: 2019-07-15 05:44:02.118 PM IST (1563192842118)\n" +
                "cellName: TestCell3\n" +
                "cellSeverName: llabwas6.uu.deere.com\n" +
                "connectorHost: llabwas6.uu.deere.com\n" +
                "connectorPort: 8879\n" +
                "connectorType: SOAP\n" +
                "contextRoot: IafDeployment\n" +
                "action: showConfirmation\n" +
                "priorAction: showList\n" +
                "entryAction: DeployAction\n" +
                "requestServerName: localhost\n" +
                "requestServerPort: 8080\n" +
                "requestURI: /IafDeployment/maint/deploy.do\n" +
                "userAuthID: IP8C7ZS\n" +
                "userAuthName: Kishor Bankar\n" +
                "userAuthEmailAddress: BankarKishorS@JohnDeere.com\n" +
                "userRacfGroups: $IMS10G^$IMS20G^$IMS21G^$IMS30G^$RMDS10^$RMDS20^$TSO11^$TSO12^$TSO20^$TSO21^$TSO30^$TSO31^ADVIAFP^ADVIAFT^OPU^U90IAF^U90IAFAD^U90IAFT\n" +
                "jvmRacfGroup: \n" +
                "sessionScopeKey: scopeMaintForm\n" +
                "resultMessage: \n" +
                "hasAdminAuthority: true\n" +
                "hasOperationsAuthority: false\n" +
                "isScopeEnabled: N\n" +
                "machineName: llabwas6\n" +
                "jvmName: iaf\n" +
                "applicationName: \n" +
                "\tat com.deere.u90.iaf.deployment.application.uccontrollers.DeployUCController.checkForContextRootChange(DeployUCController.java:78)\n" +
                "\tat com.deere.u90.iaf.deployment.struts.actions.DeployAction.confirmationPresentation(DeployAction.java:145)\n" +
                "\tat com.deere.u90.iaf.deployment.struts.actions.DeployAction.performAction(DeployAction.java:59)\n" +
                "\tat com.deere.u90.iaf.deployment.struts.actions.ApplicationBaseAction.perform(ApplicationBaseAction.java:130)\n" +
                "\tat org.apache.struts.action.Action.execute(Action.java:420)\n" +
                "\tat org.apache.struts.action.RequestProcessor.processActionPerform(RequestProcessor.java:484)\n" +
                "\tat org.apache.struts.action.RequestProcessor.process(RequestProcessor.java:274)\n" +
                "\tat org.apache.struts.action.ActionServlet.process(ActionServlet.java:1482)\n" +
                "\tat org.apache.struts.action.ActionServlet.doPost(ActionServlet.java:525)\n" +
                "\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:707)\n" +
                "\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:790)\n" +
                "\tat com.ibm.ws.webcontainer.servlet.ServletWrapper.service(ServletWrapper.java:1234)\n" +
                "\tat com.ibm.ws.webcontainer.servlet.ServletWrapper.handleRequest(ServletWrapper.java:778)\n" +
                "\tat com.ibm.ws.webcontainer.servlet.ServletWrapper.handleRequest(ServletWrapper.java:477)\n" +
                "\tat com.ibm.ws.webcontainer.servlet.ServletWrapperImpl.handleRequest(ServletWrapperImpl.java:178)\n" +
                "\tat com.ibm.ws.webcontainer.filter.WebAppFilterChain.invokeTarget(WebAppFilterChain.java:143)\n" +
                "\tat com.ibm.ws.webcontainer.filter.WebAppFilterChain.doFilter(WebAppFilterChain.java:96)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:316)\n" +
                "\tat org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:126)\n" +
                "\tat org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:90)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:114)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:122)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:169)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:48)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.authentication.www.BasicAuthenticationFilter.doFilterInternal(BasicAuthenticationFilter.java:158)\n" +
                "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:205)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:120)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:64)\n" +
                "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:91)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:53)\n" +
                "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n" +
                "\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330)\n" +
                "\tat org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:213)\n" +
                "\tat org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:176)\n" +
                "\tat org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:346)\n" +
                "\tat org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:262)\n" +
                "\tat com.ibm.ws.webcontainer.filter.FilterInstanceWrapper.doFilter(FilterInstanceWrapper.java:197)\n" +
                "\tat com.ibm.ws.webcontainer.filter.WebAppFilterChain.doFilter(WebAppFilterChain.java:90)\n" +
                "\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n" +
                "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n" +
                "\tat com.ibm.ws.webcontainer.filter.FilterInstanceWrapper.doFilter(FilterInstanceWrapper.java:197)\n" +
                "\tat com.ibm.ws.webcontainer.filter.WebAppFilterChain.doFilter(WebAppFilterChain.java:90)\n" +
                "\tat org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter.doFilter(OAuth2ClientContextFilter.java:60)\n" +
                "\tat org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:346)\n" +
                "\tat org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:262)\n" +
                "\tat com.ibm.ws.webcontainer.filter.FilterInstanceWrapper.doFilter(FilterInstanceWrapper.java:197)\n" +
                "\tat com.ibm.ws.webcontainer.filter.WebAppFilterChain.doFilter(WebAppFilterChain.java:90)\n" +
                "\tat com.deere.config.AccessDeniedFilter.doFilter(AccessDeniedFilter.java:24)\n" +
                "\tat com.ibm.ws.webcontainer.filter.FilterInstanceWrapper.doFilter(FilterInstanceWrapper.java:197)\n" +
                "\tat com.ibm.ws.webcontainer.filter.WebAppFilterChain.doFilter(WebAppFilterChain.java:90)\n" +
                "\tat com.ibm.ws.webcontainer.filter.WebAppFilterManager.doFilter(WebAppFilterManager.java:969)\n" +
                "\tat com.ibm.ws.webcontainer.filter.WebAppFilterManager.invokeFilters(WebAppFilterManager.java:1109)\n" +
                "\tat com.ibm.ws.webcontainer.servlet.CacheServletWrapper.handleRequest(CacheServletWrapper.java:82)\n" +
                "\tat com.ibm.ws.webcontainer.WebContainer.handleRequest(WebContainer.java:962)\n" +
                "\tat com.ibm.ws.webcontainer.WSWebContainer.handleRequest(WSWebContainer.java:1817)\n" +
                "\tat com.ibm.ws.webcontainer.channel.WCChannelLink.ready(WCChannelLink.java:382)\n" +
                "\tat com.ibm.ws.http.channel.inbound.impl.HttpInboundLink.handleDiscrimination(HttpInboundLink.java:465)\n" +
                "\tat com.ibm.ws.http.channel.inbound.impl.HttpInboundLink.handleNewRequest(HttpInboundLink.java:532)\n" +
                "\tat com.ibm.ws.http.channel.inbound.impl.HttpInboundLink.processRequest(HttpInboundLink.java:318)\n" +
                "\tat com.ibm.ws.http.channel.inbound.impl.HttpICLReadCallback.complete(HttpICLReadCallback.java:88)\n" +
                "\tat com.ibm.ws.tcp.channel.impl.AioReadCompletionListener.futureCompleted(AioReadCompletionListener.java:175)\n" +
                "\tat com.ibm.io.async.AbstractAsyncFuture.invokeCallback(AbstractAsyncFuture.java:217)\n" +
                "\tat com.ibm.io.async.AsyncChannelFuture.fireCompletionActions(AsyncChannelFuture.java:161)\n" +
                "\tat com.ibm.io.async.AsyncFuture.completed(AsyncFuture.java:138)\n" +
                "\tat com.ibm.io.async.ResultHandler.complete(ResultHandler.java:204)\n" +
                "\tat com.ibm.io.async.ResultHandler.runEventProcessingLoop(ResultHandler.java:775)\n" +
                "\tat com.ibm.io.async.ResultHandler$2.run(ResultHandler.java:905)\n" +
                "\tat com.ibm.ws.util.ThreadPool$Worker.run(ThreadPool.java:1892)\n" +
                "Caused by: com.deere.u90.iaf.deployment.exceptions.ApplicationException: Error attempting to determine if Context Root(s) changed.\n" +
                "EAR File = IafFrameworks_2018-11-21_13-23-56.ear\n" +
                "Application = IafFrameworks\n" +
                "old = \n" +
                "new = \n" +
                "\tat com.deere.u90.iaf.deployment.utility.ContextRoots.isContextRootChange(ContextRoots.java:68)\n" +
                "\tat com.deere.u90.iaf.deployment.application.uccontrollers.DeployUCController.checkForContextRootChange(DeployUCController.java:72)\n" +
                "\t... 81 more\n" +
                "Caused by: com.deere.u90.iaf.deployment.exceptions.ApplicationException: Error retrieving Context Roots from 'C:\\www\\uploads\\llabwas6\\iaf\\IafFrameworks_2018-11-21_13-23-56.ear'.\n" +
                "File not found.\n" +
                "\tat com.deere.u90.iaf.deployment.utility.ZipHelper.getContextRoots(ZipHelper.java:54)\n" +
                "\tat com.deere.u90.iaf.deployment.utility.ContextRoots.getEarContextRoots(ContextRoots.java:83)\n" +
                "\tat com.deere.u90.iaf.deployment.utility.ContextRoots.isContextRootChange(ContextRoots.java:53)\n" +
                "\t... 82 more";

        LOG.log(Level.INFO, response);

        return response;
    }
}