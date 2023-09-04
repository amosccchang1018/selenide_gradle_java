#!/bin/bash

if [ -d "/var/jenkins_home" ]; then
  # Running inside Jenkins container
  SCRIPT_DIR="/var/jenkins_home/workspace"
else
  # Running on local machine
  SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
fi

PROJECT_DIR="$SCRIPT_DIR/AutomationTrello"

cd "$PROJECT_DIR"

TEST_CASE=$1
TEST_CASE_FILE="com/testcases/$TEST_CASE.java"

#mvn clean test -Dtest=com.testcases.CucumberRunnerTest
if [ -f "src/test/java/$TEST_CASE_FILE" ]; then
  mvn clean test -Dtest=com.testcases.$TEST_CASE
else
  echo "Invalid test case name or file does not exist: $TEST_CASE"
fi

