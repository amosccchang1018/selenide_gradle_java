@echo off
setlocal

if exist "C:\var\jenkins_home" (
  rem Running inside Jenkins container
  set "SCRIPT_DIR=C:\var\jenkins_home\workspace"
) else (
  rem Running on local machine
  set "SCRIPT_DIR=%~dp0"
)

set "PROJECT_DIR=%SCRIPT_DIR%"
cd /d "%PROJECT_DIR%"

set "TEST_CLASS=%~1"
set "TEST_METHOD=%~2"
set "REPORT=%~3"
set "SERVE=%~4"

rem If no test method is specified, just use the class name
if "%TEST_METHOD%"=="" (
    set "FULL_TEST_NAME=com.scripts.%TEST_CLASS%"
) else (
    set "FULL_TEST_NAME=com.scripts.%TEST_CLASS%.%TEST_METHOD%"
)

rem Run the test or the whole test class if no specific method is specified
call gradlew test --tests "%FULL_TEST_NAME%"

rem Generate Allure Report if report is set to true
if "%REPORT%"=="true" (
  call gradlew allureReport
)

rem Serve Allure Report if serve is set to true
if "%SERVE%"=="true" (
  call gradlew allureServe
)

endlocal