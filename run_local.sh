#!/bin/bash

if [ -d "/var/jenkins_home" ]; then
  # Running inside Jenkins container
  SCRIPT_DIR="/var/jenkins_home/workspace"
else
  # Running on local machine
  SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
fi

PROJECT_DIR="$SCRIPT_DIR"
cd "$PROJECT_DIR"

TEST_CLASS=$1
TEST_METHOD=$2
REPORT="${3:-false}"
SERVE="${4:-false}"

# If no test method is specified, just use the class name
if [ -z "$TEST_METHOD" ]; then
    FULL_TEST_NAME="com.scripts.$TEST_CLASS"
else
    FULL_TEST_NAME="com.scripts.$TEST_CLASS.$TEST_METHOD"
fi

# Run the test or the whole test class if no specific method is specified
if ./gradlew test --tests "$FULL_TEST_NAME" ; then

  # Generate Allure Report if report is set to true
  if [ "$REPORT" == "true" ]; then
    ./gradlew allureReport
  fi

  # Serve Allure Report if serve is set to true
  if [ "$SERVE" == "true" ]; then
    ./gradlew allureServe
  fi

else
  echo "Failed to execute the test or test class: $FULL_TEST_NAME"
fi