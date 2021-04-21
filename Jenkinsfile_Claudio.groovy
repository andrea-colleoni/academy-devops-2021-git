pipeline {
    agent any

    /*
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }
    */

    stages {
        stage('Inizio Pipeline') {
            steps {
                echo """Inizio della pipeline: 
                - Build n. ${env. BUILD_NUMBER}
                - Data e ora: ${new Date().format('yyyy-MM-ddd HH:mm:ss')}"""
            }

            stage('Checkout da GIT') {
                steps {
                    echo "Riga di prova"
                }
            }
        }
    }
}