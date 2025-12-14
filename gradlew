#!/usr/bin/env sh

DIR="$(cd "$(dirname "$0")" && pwd)"
if [ -z "$JAVA_HOME" ]; then
  JAVA=java
else
  JAVA="$JAVA_HOME/bin/java"
fi

exec "$JAVA" -Xmx64m -Xms64m -classpath "$DIR/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
