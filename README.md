# jvm-crash

This little program crashes the JVM with a SIGSEGV in the C2 compiler.

The error occurs with Java 17 or 21 (Groovy doesn't seem to be compatible with 24).

To run it, source the environment variables and run the script - dependencies are included.

```
source env.sh
java -classpath "./_deps/*" org.codehaus.groovy.tools.GroovyStarter --main groovy.ui.GroovyMain --classpath ./src/main/java:./src/main/resources ./src/main/java/de/crash/the/jvm/srv/gen/CrashTheJvm.groovy -e
```
