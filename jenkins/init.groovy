import jenkins.model.*
import hudson.security.*
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement

def instance = Jenkins.getInstance()

// Set account and password
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount('user', '1234')
instance.setSecurityRealm(hudsonRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.save()

def plugin = instance.pluginManager.getPlugin('job-dsl')
def dslScriptLoader = new DslScriptLoader(new JenkinsJobManagement(System.out, [:], new File('.')))


// Dashboard name
String View_UI = "Trello Feature Test";
String View_API = "Trello API Test";

// UI test
String featureTest_CreateAndInvite = 'Create And Invite To Board Test'
String featureTest_ViewAndDelete = 'View Invitation And Delete Board Test'

// API test
String APITest_Dashboard = "Dashboard API Test"

String dslScript = """
pipelineJob('${featureTest_CreateAndInvite}') {
    triggers {
        cron('0 1 * * *')
    }
    definition {
        cps {
            script('''
                node {
                    stage('Build Env') {
                        echo 'Start building environment'
                    }
                    
                    stage('Test') {
                        echo 'Start testing'
                        sh '/var/jenkins_home/run_local.sh CucumberCreateAndInviteToBoardTest'
                    }
                    
                    stage('Deploy') {
                        echo 'Start deploying'
                    } 
                }
            ''')
            sandbox()
        }
    }
}

pipelineJob('${featureTest_ViewAndDelete}') {
    triggers {
        cron('0 1 * * *')
    }
    definition {
        cps {
            script('''
                node {
                    stage('Build Env') {
                        echo 'Start building environment'
                    }
                    
                    stage('Test') {
                        echo 'Start testing'
                        sh '/var/jenkins_home/run_local.sh ViewInvitationAndDeleteBoardTest'
                    }
                    
                    stage('Deploy') {
                        echo 'Start deploying'
                    } 
                }
            ''')
            sandbox()
        }
    }
}

pipelineJob('${APITest_Dashboard}') {
    triggers {
        cron('0 0 * * *')
    }
    definition {
        cps {
            script('''
                node {
                    stage('Build Env') {
                        echo 'Start building environment'
                    }
                
                    stage('Test') {
                        echo 'Start testing'
                        sh '/var/jenkins_home/run_local.sh TrelloAPITest'
                    }
                    
                     stage('Deploy') {
                        echo 'Start deploying'
                    }
                }
            ''')
            sandbox()
        }
    }
}

listView('${View_UI}') {
    jobs {
        name('${featureTest_CreateAndInvite}')
        name('${featureTest_ViewAndDelete}')
    }
        columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}
listView('${View_API}') {
    jobs {
        name('${APITest_Dashboard}')
    }
        columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}
"""

dslScriptLoader.runScript(dslScript)