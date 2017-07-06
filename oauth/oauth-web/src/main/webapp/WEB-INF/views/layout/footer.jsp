<div>
  <hr>
  <p align="right">
    JVM version : <span id="jvmVersion"><%= System.getProperty("java.version") %></span>
  </p>
  <p align="right">
    AP Server : <span id="apServerName"><spring:eval
        expression="@environment.getProperty('application.server.name')" /></span> <span
      id="apServerVersion"><spring:eval
        expression="@environment.getProperty('application.server.version')" /></span>
  </p>
</div>