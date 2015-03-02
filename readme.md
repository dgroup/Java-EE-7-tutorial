  
## Servlet 3.x example
This is simple example of servlet & jsp technologies.
If you want to run this example, please perform steps below:

1. Download [demo project](https://github.com/dgroup/Servlets_demo_3.x/archive/master.zip);
2. Install [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html);
2. Install [gradle](https://www.gradle.org/downloads). For **Linux OS** you can use this [guide](https://github.com/dgroup/Servlets_demo/wiki/%5BOS-Linux-Ubuntu%5D-Java-&-Gradle-installation-notes); 
3. Call `run.bat`; 
4. Browser: [http://localhost:8080](http://localhost:8080).

## Tech overview
| Tech                               | Why                                               | Gradle plugin |
| ---------------------------------- |:--------------------------------------------------|---------------|
| [Jetty](http://eclipse.org/jetty/) | Lightweight web server.                           | [jettyEclipse](https://github.com/Khoulaiz/gradle-jetty-eclipse-plugin) |
| [H2](http://www.h2database.com/html/main.html) | Lightweight in-memory database.       | [flyway](https://github.com/ben-manes/gradle-flyway-plugin) |
| [PMD](http://pmd.sourceforge.net/) | Code quality validation.                          | [pmd](https://gradle.org/docs/current/userguide/pmd_plugin.html)               |
| [Bootstrap](https://github.com/twbs/bootstrap) | CSS layouts. Read [more](http://www.w3schools.com/bootstrap/).          |               |

Have a fun.
