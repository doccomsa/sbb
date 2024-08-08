#!/usr/bin/env bash
# stop.sh

PROJECT="sbb-1.0.0"
PROJECT_NAME=sbb
PROJECT_FULL_PATH_01="/home/ec2-user/app/step1/sbb/build/libs"
JAR_FILE="$PROJECT_FULL_PATH_01/$PROJECT.jar"
LOG_PATH="$PROJECT_FULL_PATH_01/logs"

DEPLOY_LOG="$LOG_PATH/0_deploy.log"

NOW_DATETIME=$(date "+%Y-%m-%d-%aT%T")

if [ ! -d $LOG_PATH ]; then
    mkdir $LOG_PATH
fi

CURRENT_PID=$(pgrep -f $PROJECT_NAME)

if [ -z "$CURRENT_PID" ]; then
  echo "$NOW_DATETIME :: $JAR_FILE :: There is no process!" >> $DEPLOY_LOG
else
  echo "$NOW_DATETIME :: $JAR_FILE :: $CURRENT_PID stopped!" >> $DEPLOY_LOG  
  kill -15 $CURRENT_PID
  sleep 5
fi
