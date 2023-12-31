pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/ansal300/TestNgRestAssured.git'

                // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                bat "mvn -DEnv=qa verify"
            }
        }

        stage('Publish Extent Report') {
            steps {
                // Assuming your Extent Report is generated in the report directory
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'TestReport*',
                    reportName: 'Extent Report'
                ])
            }
        }

         stage('Send Email with Extent Report') {
            steps {
                script {
                    // Attach the Extent Report to the email
                    def extentReportPath = findFiles(glob: 'reports/*.html')[0]?.toString()
                    emailext(
                        subject: 'Test Results - Jenkins Pipeline',
                        body: 'Please find attached the Extent Report for the latest build.',
                        to: 'ansal.uk300@gmail.com',
                        attachLog: true,
                        attachmentsPattern: "${extentReportPath}"
                    )
                }
            }
        }
    }

     post {
        always {
           // Archive files or directories
            archiveArtifacts artifacts: 'reports/**', fingerprint: true
                // Display a message in the console
             echo 'Artifacts archived successfully!'

        }

    }

}