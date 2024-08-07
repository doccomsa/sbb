#!/usr/bin/env bash
# start.sh

PROJECT="sbb-1.0.0"
PROJECT_FULL_PATH_01="/home/ec2-user/app/step1/sbb"
JAR_LIB="/sbb/build/libs"
JAR_FILE="$PROJECT_FULL_PATH_01$JAR_LIB/$PROJECT.jar"
LOG_PATH="$PROJECT_FULL_PATH_01/logs"

APP_LOG="$LOG_PATH/application.log"
ERROR_LOG="$LOG_PATH/error.log"
DEPLOY_LOG="$LOG_PATH/0_deploy.log"

NOW_DATETIME=$(date "+%Y-%m-%d-%aT%T")

if [ ! -d $LOG_PATH ]; then
    mkdir $LOG_PATH
fi

cd $PROJECT_FULL_PATH_01$JAR_LIB

#nohup java -jar $JAR_FILE 1>$APP_LOG 2>$ERROR_LOG &
#nohup java -jar $JAR_FILE 1>>$APP_LOG 2>>$ERROR_LOG &
nohup java -jar $JAR_FILE 1> /dev/null 2>&1 &

sleep 30s

CURRENT_PID=$(pgrep -f $JAR_FILE)

if [ -z $CURRENT_PID ]; then
  echo "$NOW_DATETIME :: $JAR_FILE :: failed to start!" >> $DEPLOY_LOG
else
  echo "$NOW_DATETIME :: $JAR_FILE :: $CURRENT_PID started!" >> $DEPLOY_LOG
fi
