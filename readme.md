  
## Servlet 3.x learning
This is simple example of servlet & jsp technologies.
If you want to run this example, please perform steps below:

1. Download [demo project](https://github.com/dgroup/Servlets_demo_3.x/archive/master.zip);
2. Install [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html);
2. Install [gradle](https://www.gradle.org/downloads). For **Linux OS** you can use this [guide](https://github.com/dgroup/Servlets_demo/wiki/%5BOS-Linux-Ubuntu%5D-Java-&-Gradle-installation-notes); 
3. Call `run.bat`;
4. Browser: [http://localhost:8080](http://localhost:8080).

## Tech overview
| Tech                               | Why                                               | 
| ---------------------------------- |:--------------------------------------------------|
| [Jetty](http://eclipse.org/jetty/) | Lightweight web server.                           | 
| [H2](http://www.h2database.com/html/main.html) | Lightweight in-memory database.       | 
| [PMD](http://pmd.sourceforge.net/) | Code quality validation.                          | 
| [Bootstrap](https://github.com/twbs/bootstrap) | CSS layouts. Read [more](http://www.w3schools.com/bootstrap/).|

### TODO list (will be updated as appropriate)
- [x] Enable Gradle
- [x] Configuration Jetty + H2
- [x] Enable PMD 
- [x] Enable Scala for EE project 
- [x] Enable Scalastyle 
- [ ] Inject CDI for Logging
- [ ] Activate [Serenity](http://thucydides.info/docs/serenity-staging/) for UI testing
- [ ] Implement simple chat (for feedback) via [WebSocket mechanism](https://docs.oracle.com/javaee/7/tutorial/websocket002.htm)
- [ ] Activate JPA instead of deprecated DriverManager
  - [x] Activation of Persistence Unit
  - [ ] Integrate CDI with EntityManager
- [ ] Learning [JEE7 documentation](https://docs.oracle.com/javaee/7/tutorial/index.html)
