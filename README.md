# jvm-crash

This little program crashes the JVM with a SIGSEGV in the C2 compiler.

The error occurs with Java 17 or 21 (Groovy doesn't seem to be compatible with 24).

To run it, source the environment variables and run the script - dependencies are included.

```
gradle copyDeps
source env.sh
java -classpath "./build/dependencies/*" org.codehaus.groovy.tools.GroovyStarter --main groovy.ui.GroovyMain --classpath ./src/main/java:./src/main/resources ./src/main/java/de/crash/the/jvm/srv/gen/CrashTheJvm.groovy -e
```

```
#
# A fatal error has been detected by the Java Runtime Environment:
#
#  SIGSEGV (0xb) at pc=0x00007244648944e0, pid=4634, tid=4677
#
# JRE version: Java(TM) SE Runtime Environment (21.0.7+8) (build 21.0.7+8-LTS-245)
# Java VM: Java HotSpot(TM) 64-Bit Server VM (21.0.7+8-LTS-245, mixed mode, sharing, tiered, compressed oops, compressed class ptrs, g1 gc, linux-amd64)
# Problematic frame:
# V  [libjvm.so+0xc944e0]  Node::uncast(bool) const+0x0
#
# Core dump will be written. Default location: /home/mroth/src/jvm-crash/core.%e.4634
#
# An error report file with more information is saved as:
# /home/mroth/src/jvm-crash/hs_err_pid4634.log
[thread 4678 also had an error]
#
# Compiler replay data is saved as:
# /home/mroth/src/jvm-crash/replay_pid4634.log
#
# If you would like to submit a bug report, please visit:
#   https://bugreport.java.com/bugreport/crash.jsp
#
[1]    4634 abort (core dumped)  java -classpath "./build/dependencies/*"  --main groovy.ui.GroovyMain    -e
