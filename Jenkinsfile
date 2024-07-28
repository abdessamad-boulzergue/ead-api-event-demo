pipeline {

  agent any

  environment {
           MVN_PROFILE = ' '
           MVN_SETTINGS = ' '
           GITHUB_REPO = 'git@github.com:abdessamad-boulzergue/keda.git'
           GITHUB_CRED_ID = 'tinmelpwd'
   }
  parameters {
     choice(name:"ENV",  choices:["DEV","INT","PrePROD","PROD"])
     string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Branch to build')
  }

  stages{
         stage("CONFIGURATION"){
            steps{
                script{
                   echo "CONFIGURATION : ${ENV}"
                }
            }
         }

         stage('Checkout') {
              steps {
                  script{
                      // Checkout the code from GitHub
                      sh(returnStdout: true, script: "git config --global http.sslVerify false")
                      deleteDir()
                      checkout scm
                      def pom = readFile('pom.xml')
                      APP_VERSION = getValueFromPattern(pom,"<version>(.+)</version>")
                      APP_ARTIFACT_ID = getValueFromPattern(pom,"<artifactId>(.+)</artifactId>")
                      APP_PACKAGING = getValueFromPattern(pom,"<packaging>(.+)</packaging>")

                      echo " APP_VERSION is : " + (APP_VERSION ?: "empty")
                      echo " APP_PACKAGING is : ${APP_PACKAGING} "
                     /*git(
                        url: "${GITHUB_REPO}",
                        branch: "${params.BRANCH_NAME}",
                        credentialsId: "${GITHUB_CRED_ID}"
                      )*/
                  }
              }
         }
         stage("compile"){
            steps{
                    script{
                       sh "mvn ${MVN_SETTINGS} clean compile test-compile ${MVN_PROFILE}"
                    }
            }
         }
  }
}

def getValueFromPattern(content, regex) {
    def matcher = content =~ regex
    if (matcher.find()) {
        return matcher.group(1) // Return the first
    }
    else {
        return ""
    }
}