pipeline {
    agent any

    environment {
        JIRA_SITE = 'https://rajeevsworkspace-29569754.atlassian.net'
        JIRA_PROJECT = 'KAN'
        JIRA_CREDENTIALS_ID = 'JIRA_API_TOKEN'
        CI_RUN = 'true'
    }

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
                bat 'docker run --rm -e CI_RUN=%CI_RUN% -v "%cd%\\test-output:/app/test-output" -v "%cd%\\traces:/app/traces" playwright-oranges-hrms'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'test-output/**', fingerprint: true
            archiveArtifacts artifacts: 'traces/**', fingerprint: true

            publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'test-output',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
            ])

            emailext(
                    to: 'kkrajeev999@gmail.com',
                    subject: "Build ${currentBuild.currentResult}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    mimeType: 'text/html',
                    body: """
                <h2>Playwright Automation Execution Report</h2>
                <p>See the build details below. If the build failed, a Jira ticket may have been created.</p>

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
                        <td><b>Extent Report</b></td>
                        <td><a href="${env.BUILD_URL}Extent_Report/">${env.BUILD_URL}Extent_Report/</a></td>
                    </tr>
                </table>

                <br>
                Regards,<br>
                Jenkins CI/CD
                """,
                    attachmentsPattern: 'test-output/**'
            )
        }

        failure {
            script {
                echo "Build failed. Creating Jira ticket..."

                def issue = [
                        fields: [
                                project: [key: env.JIRA_PROJECT],
                                summary: "Automated Test Failure in Build #${env.BUILD_NUMBER}",
                                description: "Build failed for job: ${env.JOB_NAME}.\nBuild URL: ${env.BUILD_URL}\n\nPlease check the attached logs and reports.",
                                issuetype: [name: 'Bug']
                        ]
                ]

                def newIssue = jiraNewIssue(
                        site: env.JIRA_SITE,
                        credentialsId: env.JIRA_CREDENTIALS_ID,
                        issue: issue
                )

                echo "Successfully created Jira ticket: ${newIssue.key}"

                jiraAddAttachment(
                        site: env.JIRA_SITE,
                        credentialsId: env.JIRA_CREDENTIALS_ID,
                        issueKey: newIssue.key,
                        file: 'test-output/ExtentReport.html'
                )
            }
        }
    }
}