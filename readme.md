## About The Project

This project aims to validate the functionality of the UI & API feature for a specific e-commerce website.

## Hardware Specifications

---

The recommended PC specifications for executing these test cases are as follows:

* Operating System: Mac OS
* Memory: Minimum 8GB RAM
* Disk Space: At least 256GB
* Processor: Intel i5 or equivalent

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Project Directory Structure

---

  ```sh
. 
CyclomediaAssignment_ChiChun
├── run_local.sh (Script to run test cases)
├── runJenkins.sh (Script to run test cases in container and manage with Jenkins)
├── .gitignore
├── readme.md
├── jenkins
│   ├── Dockerfile-jenkins 
│   ├── plugins.txt  (Jenkins plugin management)
│   └── init.groovy  (Jenkins pipeline scripts)
└── automationTrello
    ├── src
    │   ├── main
    │   └── test
    │       └── java
    │       │     └── com
    │       │         ├── constants 
    │       │         ├── utils     (Functions for UI/API/DB ... actions)
    │       │         └── testcases (Test cases for the application)
    │       │             ├── TrelloAPITest (API test)
    │       │             ├── CucumberCreateAndInviteToBoardTest.java (UI test for triggering .feature)
    │       │             ├── ViewInvitationAndDeleteBoardTest.java   (UI test for triggering .feature)
    │       │             └── ...
    │       └── resources
    │            └── com
    │                └── features 
    │                │   ├── CucumberCreateAndInviteToBoardTest.feature (Cucumber file)
    │                │   └── ViewInvitationAndDeleteBoardTest.feature   (Cucumber file)
    │                │
    │                ├── account.properties (Account management)
    │                ├── env.properties     (Environment setting) 
    │                └── cucumber.properties
    │ 
    └── target
        ├── pom.xml (Dependencies)
        ├── cucumber-reports (Folder where report generated on execution)
        ├── init.groovy (Jenkin pipline scripts)
        └── ...
    
  ```


<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Prerequisites

---

The following tools must be installed to execute this project:

1. [docker](https://docs.docker.com/engine/install/)
2. [Java 11](https://www.oracle.com/java/technologies/downloads/) - Note: While the assignment specifies a preferred language, I'm currently using Java, but with all the same frameworks as requested in the requirements.(for local execution)
3. [Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) (for local execution)
3. IDE [IntelliJ](https://www.jetbrains.com/idea/download/) (for local execution)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## About test cases

---

### How to set domain in the beginning (Optional - For Further Development)

To set desired domain, navigate to the following file:

```sh
cd path/to/CyclomediaAssignment_ChiChun/AutomationTrello/src/test/resources/env.properties
```
In the env.properties file, you are able to set the environment first.

![Imgur](https://imgur.com/HgZn8vW.png)

### About test cases
![Imgur](https://i.imgur.com/aygI0rC.png) 

The test case design is based on the documented requirements, as illustrated in the figures above. The required features (represented by the green and blue blocks) are located in the following files:

a. TrelloAPITest (API test - optional)

b. CucumberCreateAndInviteToBoardTest.feature (UI test - create and invite)

c. ViewInvitationAndDeleteBoardTest.feature   (UI test - view and delete)

To access them, navigate to the following directory:

```sh
cd path/to/CyclomediaAssignment_ChiChun/AutomationTrello/src/test/java/com/testcases
```

and 
```sh
cd path/to/CyclomediaAssignment_ChiChun/AutomationTrello/src/test/resources/com/features
```

Note : Due to the **two-factor authentication** required for Google accounts in the `production environment`, many **UI steps in the feature test have been temporarily replaced with API requests and responses** to ensure smoother testing. 

![Imgur](https://i.imgur.com/xm2uElH.png)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Execution

---

* [Local Execution with IDE](#local-execution-with-ide)
* [Execution with shell script (no need to use IDE)](#execution-with-shell-script-no-need-to-use-ide)
* [Execution in container and manage with Jenkins](#execution-in-container-and-manage-with-jenkins)
* [Automatically generated report](#automatically-generated-report)

### Local Execution with IDE

1. Install the **prerequisites** listed above in your local environment.
2. Launch the **IntelliJ IDE** after installation. 
3. Upon initial launch, the project needs to be built with the necessary configurations and dependencies. To do this, click on **Maven** > **Refresh icon** (Reload All Maven Projects). This might take a while, so your patience is appreciated.
  
   ![Imgur](https://i.imgur.com/WETlznc.png)

4. Choose a specific test case or all test cases in a class, then click the run icon.

   ![Imgur](https://i.imgur.com/1kDawbR.png)

5. After clicking, select "Debug '____Test' ".

   ![Imgur](https://i.imgur.com/CTKu5XW.png)

6. Wait for the execution to complete. The results and any error messages (useful for troubleshooting) will be displayed in the Debugger console.
7. 
   ![Imgur](https://i.imgur.com/q4zIBKn.png)



### Execution with shell script (no need to use IDE)

Follow these steps to execute the test cases shell script:

1. Navigate to the path where the repository is located and locate the shell script `run.sh`.
```bash
## For Linux/Mac users:
cd path/to/CyclomediaAssignment_ChiChun/

##  For Windows users:
cd path\to\CyclomediaAssignment_ChiChun\
```
2. Execute the `run.sh` with parameter - test cases in your terminal.

```bash
# For Linux/Mac users:

## Permissions
chmod +x run.sh

## Execution
# Add your test case name as a parameter, for example: "CucumberCreateAndInviteToBoardTest"
./run.sh <test_case_name>


# For Windows users:

## Execution
REM Add your test case name as a parameter, for example: "CucumberCreateAndInviteToBoardTest"
run.bat <test_case_name>
``` 

3. After executing `./run.sh <test_case_name>` or `run.bat <test_case_name>`, dependencies will be installed, and the test cases will be run. The results will be displayed in the terminal.

 ![Imgur](https://i.imgur.com/JYiI29W.png)


### Execution in container and manage with Jenkins

* Prerequsite: Please help make sure you have **open docker** and **see it's running(mac/win)**.

 ![Imgur](https://i.imgur.com/iLm8C96.png)

For setting up the docker container, navigate to the repository directory first, then locate and execute the specific shell script or batch file based on your operating system:

```sh
## For Linux/Mac users:
cd path/to/CyclomediaAssignment_ChiChun/
./runJenkins.sh

##  For Windows users:
cd path\to\CyclomediaAssignment_ChiChun\
runJenkins.bat
```

After running the script, open a web browser and navigate to http://localhost:8080/. You'll be redirected to the Jenkins dashboard, as shown below:

![Imgur](https://i.imgur.com/i5AU08V.png)

![Imgur](https://i.imgur.com/TVGUyi1.png)

From this dashboard, you can manage and trigger test cases using the UI interface, eliminating the need to operate exclusively within the terminal environment.

Note-1: the username, password, and time for automatic run are set in the file `init.groovy`

Note-2: The shell script will build a container with the following components:

*1.Linux*

*2.Jenkins*

*3.Java 11*

*4.Maven*

*5.Selenium*

*6.Cucumber*

*7.testNG*
## Automatically generated report
When the test cases is triggered by either shell script or on jenkins, the test report will be generated automatically and located in the folder 

For feature(UI) test report : 

```sh
cd path/to/CyclomediaAssignment_ChiChun/AutomationTrello/target/cucumber-reports
```

![Imgur](https://i.imgur.com/x03LJ6c.png)

or

For API test report :

```sh
cd path/to/CyclomediaAssignment_ChiChun/AutomationTrello/target/testng_report_<timestamp>
```

![Imgur](https://i.imgur.com/MNO6GXo.png)


And the reports will display the steps and errors in detail

Feature(UI) test report : 
![Imgur](https://i.imgur.com/Nq4amGw.png)

API test report :
![Imgur](https://i.imgur.com/9nwuu7v.png)


<p align="right">(<a href="#readme-top">back to top</a>)</p>




## Future Testing Plans and Infrastructure Recommendations

---

* Set up SonarQube (Code Quality)
* Integrate with CircleCI or GitHub Actions (CI tools)
* Manage test cases with TestRail, Xray or Jira (Test Case Management)
* Complete the Jenkins configuration
* Utilize tools like Confluence and Jira for better project management.
* Finalize the infrastructure framework.

#### Infrastructure for future test plans

Utilizing the provided template, we will comprehensively analyze each feature and design corresponding test cases.

![Imgur](https://i.imgur.com/S7oXs7i.png)

#### Infrastructure suggestion for test automation

![Imgur](https://i.imgur.com/ZMuYPVX.png)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contact Me

---


Feel free to reach out via email or LinkedIn if you have any suggestions or innovative ideas for the enhancement of this project.

Email : amosccchang@gmail.com

LinkedIn : https://www.linkedin.com/in/ccchang1018/

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Acknowledgments

The following acknowledgments should provide insights into this project if certain aspects are unfamiliar to you. Please review them for a better understanding of the project.
* [Building Java Projects with Maven](https://spring.io/guides/gs/maven/)
* [Selenium Tutorial](https://www.javatpoint.com/selenium-tutorial)
* [Cucumber Tutorial](https://www.tutorialspoint.com/cucumber/index.htm)
* [TestNG tutorial](https://testng.org/doc/)
* [The Java™ Tutorials](https://docs.oracle.com/javase/tutorial/)
* [Overview | Docker Documentation](https://docs.docker.com/get-started/)
* [Dockerfile tutorial](https://www.tutorialspoint.com/docker/docker_file.htm)
* [Jenkins Tutorial](https://www.jenkins.io/doc/tutorials/)
* [Hands-On Tutorials for Amazon Web Services (AWS)](https://aws.amazon.com/getting-started/hands-on/)
* [Databricks documentation](https://docs.databricks.com/?_gl=1*1kanlbo*_gcl_au*ODU2MTA0NzA4LjE2OTAxNjg0NTc.*rs_ga*OTMzNjJmZjUtOTg1My00ODI2LTgzNTYtYjc2NDc2YmRlM2Vi*rs_ga_PQSEQ3RZQC*MTY5MDE2ODQ1NjIwNi4xLjAuMTY5MDE2ODQ1OS42MC4wLjA.&_ga=2.229993884.180442662.1690168457-1076022423.1690168457)
* [Grafana tutorials](https://grafana.com/tutorials/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
