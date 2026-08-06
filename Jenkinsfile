// Declarative Pipeline
pipeline {
    // 1. Agent Selection: Run on any available Jenkins agent
    agent any

    // 2. Environment Variables: Define variables for use in the pipeline
    environment {
        DOCKER_IMAGE_NAME = 'playwright-oranges-hrms'
    }

    // 3. Stages: Define the main steps of the CI/CD process
    stages {

        // Stage 1: Get the source code
        stage('Checkout Source Code') {
            steps {
                script {
                    echo "Checking out code from Version Control System..."
                    checkout scm
                }
            }
        }

        // Stage 2: Build the test environment as a Docker image
        stage('Build Test Environment') {
            steps {
                script {
                    echo "Building Docker image: ${DOCKER_IMAGE_NAME}..."
                    // **FIX:** Use double quotes to allow variable interpolation
                    bat "docker build -t ${DOCKER_IMAGE_NAME} ."
                }
            }
        }

        // Stage 3: Execute the Playwright tests inside the Docker container
        stage('Execute Automated Tests') {
            steps {
                script {
                    echo "Running Playwright tests inside the Docker container..."
                    // **FIX:** Use double quotes to allow variable interpolation
                    bat "docker run --rm -v \\"%cd%/test-output:/app/test-output\\" -v \\"%cd%/traces:/app/traces\\" ${DOCKER_IMAGE_NAME}"
                }
            }
        }
    }

    // 4. Post-Build Actions: These steps run after all stages are complete
    post {
        // 'always' ensures these steps run whether the build succeeds or fails
        always {
            
            // Step 1: Archive test artifacts (reports and traces)
            script {
                echo "Archiving test artifacts..."
                archiveArtifacts artifacts: 'test-output/**', fingerprint: true
                archiveArtifacts artifacts: 'traces/**', fingerprint: true
            }

            // Step 2: Publish the HTML test report
            script {
                echo "Publishing HTML report..."
                publishHTML(target: [
                        allowMissing: true,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'test-output',
                        reportFiles: 'ExtentReport.html',
                        reportName: 'Extent Report'
                ])
            }

            // Step 3: Send an email notification with the results
            script {
                echo "Sending email notification..."
                emailext(
                        to: 'kkrajeev999@gmail.com',
                        subject: "Build ${currentBuild.currentResult}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                        mimeType: 'text/html',
                        body: """
                    <h2>Playwright Automation Execution Report</h2>
                    <table border="1" cellpadding="6">
                        <tr><td><b>Project</b></td><td>${env.JOB_NAME}</td></tr>
                        <tr><td><b>Build Number</b></td><td>${env.BUILD_NUMBER}</td></tr>
                        <tr><td><b>Status</b></td><td>${currentBuild.currentResult}</td></tr>
                        <tr><td><b>Build URL</b></td><td><a href="${env.BUILD_URL}">${env.BUILD_URL}</a></td></tr>
                        <tr><td><b>Console Log</b></td><td><a href="${env.BUILD_URL}console">${env.BUILD_URL}console</a></td></tr>
                        <tr><td><b>Extent Report</b></td><td><a href="${env.BUILD_URL}Extent_Report/">${env.BUILD_URL}Extent_Report/</a></td></tr>
                    </table>
                    <br>
                    Regards,<br>
                    Jenkins CI/CD
                    """,
                        attachmentsPattern: 'test-output/**'
                )
            }
        }
    }
}
