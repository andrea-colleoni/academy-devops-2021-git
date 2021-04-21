def buildDate = new Date().format('yyyy-MM-dd HH:mm:ss')

pipeline {
    agent any
    /*
    tools {
        maven 'Maven 3.8.1'
    }
    */
    stages {
        stage('Inizio Pipeline') {
            steps {
                echo """Inizio della pipeline: 
                - Build n. ${env.BUILD_NUMBER} 
                - Data e ora: $buildDate"""
            }
        }
        stage('Write build info') {
            steps {
                writeFile encoding: 'UTF-8', file: 'info.md', text: """# Informazioni di build

                - Job name: ${env.JOB_NAME}
                - Build number: ${env.BUILD_NUMBER}
                - Build date: $buildDate"""
            }
        }
        stage('Checkout da GIT') {
            steps {
                git branch: 'main', url: 'https://github.com/andrea-colleoni/academy-devops-2021-git.git'
                sh 'ls'
            }
        }
        stage('Maven Compile') {
            steps {
                withMaven(maven: 'Maven 3.8.1') {
                    sh 'mvn compile -f primi-tests/pom.xml'
                }
            }
        }
        stage('Maven Test') {
            steps {
                withMaven(maven: 'Maven 3.8.1') {
                    sh 'mvn test -f primi-tests/pom.xml -Dwebdriver.chrome.driver=../resources/mac/chromedriver'
                }                
            }
        }
        /*
        stage('Fail!') {
            steps {
                bat 'pippo'
            }
        }
        */
    }
    post {
      always {
        junit 'primi-tests/target/surefire-reports/*.xml'
      }
      success {
        zip archive: true,   dir: '',   exclude: '', glob: '',  overwrite: true,  zipFile: "${env.JOB_NAME}_${env.Build_NUMBER}.zip"
      }      
      failure {
        emailext (
            body: "La build numero ${env.BUILD_NUMBER}  del job ${env.JOB_NAME} è fallita.", 
            subject: "Pipeline ${env.JOB_NAME} fallito", 
            to: 'andrea@colleoni.info'
        )
      }      
    }
}