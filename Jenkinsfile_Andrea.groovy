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
                - Data e ora: ${new Date().format('yyyy-MM-ddd HH:mm:ss')}"""
            }
        }
        stage('Checkout da GIT') {
            steps {
                git branch: 'main', url: 'https://github.com/andrea-colleoni/academy-devops-2021-git.git'
                bat 'dir'
            }
        }
        stage('Maven Compile') {
            steps {
                withMaven(maven: 'Maven 3.8.1') {
                    bat 'mvn compile -f primi-tests/pom.xml'
                }
            }
        }
        stage('Maven Test') {
            steps {
                withMaven(maven: 'Maven 3.8.1') {
                    bat 'mvn test -f primi-tests/pom.xml -Dwebdriver.gecko.driver=/C:/Users/andre/Desktop/Temp/Corsi/D-Thinks/DevOps/geckodriver-v0.29.1-win64/geckodriver.exe'
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
      success {
        junit 'primi-tests/target/surefire-reports/*.xml'
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