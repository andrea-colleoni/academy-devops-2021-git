def buildDate = new Date().format('yyyy-MM-dd HH:mm:ss')

pipeline {
    agent any
   /* tools {
        maven 'Maven 3.8.1'
    } 
    */
    
    stages {
        stage('Inizio Pipeline') {
            steps {
                echo """Inizio della Pipeline 
                - Build n. ${env.BUILD_NUMBER}
                -Data e ora: $buildDate"""
            }
        }
        
        stage('Checkout da Git'){
            steps{
                git branch: 'main', url: 'https://github.com/andrea-colleoni/academy-devops-2021-git.git' 
                sh 'ls -lrt'
            }
        }
        
        stage('Maven compile'){
            steps{
                withMaven(maven: 'Maven 3.8.1') {
                   sh 'mvn compile -f primi-tests/pom.xml'
                }
            }
        }
        
        stage('Maven test'){
            steps{
                withMaven(maven: 'Maven 3.8.1') {
                   sh 'mvn test -f primi-tests/pom.xml -Dwebdriver.gecko.driver=/Users/matteo/Documents/CloudDevOps/geckodriver/geckodriver'
                }
            }
        }
        
        /*stage('pippippi'){
            steps{
                sh 'ppppccdcd'
            }
        }*/
        
    }
    post {
        success {
            junit 'primi-tests/target/surefire-reports/TEST-algebra.CalcoliTest.xml'
           zip archive: true, dir: '', exclude: '', glob: '', overwrite: true, zipFile: '$(env.JOB_NAME)_$(env.Build_NUMBER).zip'
        }
        
       /* failure {
            emailext ( body: "La build numero ${env.BUILD_NUMBER} del job è fallita.",
            subject: "Pipeline ${env.JOB_NAME} fallito", to: 'matteo.loddo@gmail.com')  
        } */
    }

}
