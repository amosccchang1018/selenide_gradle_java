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
String View_UI = "UI Feature Test";
String View_API = "API Test";
String View_APIChain = "API Chaining Test";

// UI test
String featureTest_AddRingToFavorite = 'Add Specific Ring To Favorite Test'

// API test
String APITest = 'Related API Test'
String APIChainTest = 'Related API Chaining Test'

String dslScript = """
pipelineJob('${featureTest_AddRingToFavorite}') {
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
                        sh '/var/jenkins_home/run_local.sh WebE2ETest findProductAndAddToCartTest'
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

pipelineJob('${APITest}') {
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
                        sh '/var/jenkins_home/run_local.sh APITest'
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

pipelineJob('${APIChainTest}') {
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
                        sh '/var/jenkins_home/run_local.sh APIChainingTest'
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
        name('${featureTest_AddRingToFavorite}')
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
        name('${APITest}')
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
listView('${View_APIChain}') {
    jobs {
        name('${APIChainTest}')
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