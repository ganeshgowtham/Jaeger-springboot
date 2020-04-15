#! /bin/sh


export LIBS=./libs
export APP=com.github.ganeshgowtham.jaeger.BootstrapPaymentApplication
export LOG_DIR=${APP_HOME}/logs
export CLASSPATH=${CLASSPATH}:${LIBS}/*
java -enableassertions -cp ${CLASSPATH} ${LOGBACKCFG} ${APP}

