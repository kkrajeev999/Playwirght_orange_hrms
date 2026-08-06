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

            archiveArtifacts artifacts: 'Reports/**', fingerprint: true

            publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'Reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
            ])

            emailext(
                    to: 'kkrajeev999@gmail.com',

                    subject: "Build ${currentBuild.currentResult}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",

                    mimeType: 'text/html',

                    body: """
                <h2>Playwright Automation Execution Report</h2>

                <table border="1" cellpadding="6">
                    <tr>
                        <td><b>Project</b></td>
                        <td>${env.JOB_NAME}</td>
                    </tr>

                    <tr>
                        <td><b>Build Number</b></td>
                        <td>${env.BUILD_NUMBER}</td>
                    </tr>

                    <tr>
                        <td><b>Status</b></td>
                        <td>${currentBuild.currentResult}</td>
                    </tr>

                    <tr>
                        <td><b>Build URL</b></td>
                        <td><a href="${env.BUILD_URL}">${env.BUILD_URL}</a></td>
                    </tr>

                    <tr>
                        <td><b>Console Log</b></td>
                        <td><a href="${env.BUILD_URL}console">${env.BUILD_URL}console</a></td>
                    </tr>

                    <tr>
                        <td><b>Extent Report</b></td>
                        <td><a href="${env.BUILD_URL}Extent_Report/">${env.BUILD_URL}Extent_Report/</a></td>
                    </tr>
                </table>

                <br>

                Regards,<br>
                Jenkins CI/CD
                """,

                    attachmentsPattern: 'Reports/**'
            )
        }
    }
}