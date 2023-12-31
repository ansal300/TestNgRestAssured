pipeline {
    agent any

     triggers {
        cron('0 0 * * 1-5') // Run at 12 AM every day (Monday to Friday)

        //cron('30 6 * * 1,3,5') // Run at 6:30 AM every Monday, Wednesday, and Friday

         //cron('0 6 15 * *') // Run at 6 AM on the 15th day of the month

         //cron('0 18 15 * *') // Run at 6 PM on the 15th day of the month
    }

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
                        to: 'ansaluk300@gmail.com',
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