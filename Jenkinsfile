pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t playwright-oranges-hrms .'
            }
        }

        stage('Run Playwright Tests') {
            steps {
                bat 'docker run --rm playwright-oranges-hrms'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'test-output/**', fingerprint: true

            publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'Reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
            ])
        }
    }
}