## Get started

  
### 1. Run the Application
Once you've provided the API key and started a PostgreSQL database you can run the application using

```bash
mvn spring-boot:run
mvn test

```

The application will start on port `8080` so you can send a sample request to `http://localhost:8080/hello` to see if you're up and running.

### 4. Start Standalone WireMock server
To start WireMock standalone use the provided `startWireMockServer.sh`. This script starts a Wiremock Standalone server and post some data so later we can run test against this.
  * port: `8091`
  * endpoint: `localhost:8091/weathher`