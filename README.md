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
#  SIGSEGV (0xb) at pc=0x00007dc39b33784b, pid=103083, tid=103228
#
# JRE version: OpenJDK Runtime Environment (21.0.8) (fastdebug build 21.0.8-internal-adhoc.mroth.jdk21u)
# Java VM: OpenJDK 64-Bit Server VM (fastdebug 21.0.8-internal-adhoc.mroth.jdk21u, mixed mode, sharing, tiered, compressed oops, compressed class ptrs, g1 gc, linux-amd64)
# Problematic frame:
# V  [libjvm.so+0x73784b]  InlineTree::try_to_inline(ciMethod*, ciMethod*, int, JVMState*, ciCallProfile&, bool&)+0x74b
#
# Core dump will be written. Default location: /var/coredump/core.103083
#
# An error report file with more information is saved as:
# /home/mroth/src/jvm-crash/hs_err_pid103083.log
#
# Compiler replay data is saved as:
# /home/mroth/src/jvm-crash/replay_pid103083.log
#
# If you would like to submit a bug report, please visit:
#   https://bugreport.java.com/bugreport/crash.jsp
#
[1]    103083 IOT instruction  java -classpath "./build/dependencies/*"  --main groovy.ui.GroovyMain    -e

