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
#  SIGSEGV (0xb) at pc=0x000078b6440b58e0, pid=18702, tid=18731
#
# JRE version: OpenJDK Runtime Environment Corretto-21.0.7.6.1 (21.0.7+6) (build 21.0.7+6-LTS)
# Java VM: OpenJDK 64-Bit Server VM Corretto-21.0.7.6.1 (21.0.7+6-LTS, mixed mode, tiered, compressed oops, compressed class ptrs, g1 gc, linux-amd64)
# Problematic frame:
# V  [libjvm.so+0xcb58e0]  Node::uncast(bool) const+0x0
#
# Core dump will be written. Default location: Core dumps may be processed with "/usr/share/apport/apport -p%p -s%s -c%c -d%d -P%P -u%u -g%g -- %E" (or dumping to /home/mroth/src/jvm-crash/core.18702)
#
# An error report file with more information is saved as:
# /home/mroth/src/jvm-crash/hs_err_pid18702.log
#
# Compiler replay data is saved as:
# /home/mroth/src/jvm-crash/replay_pid18702.log
#
# If you would like to submit a bug report, please visit:
#   https://github.com/corretto/corretto-21/issues/
#
[1]    18702 IOT instruction (core dumped)  java -Xverify:all -classpath "./_deps/*"  --main groovy.ui.GroovyMain    -e
```
