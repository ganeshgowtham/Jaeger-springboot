#! /bin/sh
echo "I am starting .."
export LIBS=/app/JaegerTutorial/libs
export APP=com.github.ganeshgowtham.jaeger.BootstrapPaymentApplication
export LOG_DIR=${APP_HOME}/logs
export CLASSPATH=${CLASSPATH}:${LIBS}/*
${JAVA_HOME}/bin/java -enableassertions -cp ${CLASSPATH} ${LOGBACKCFG} ${APP}