def params = [:] // Declare params as a map

pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Use the credentials defined in Jenkins
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/main']],
                          userRemoteConfigs: [[
                              url: 'https://github.com/vishakha27kalra/testing.git',
                              credentialsId: 'github-creds' // Refer to the credentials added in Jenkins
                          ]]
                ])
                script {
                    // Fetching in-built variables after checkout
                    
                    def gitCommitMessage = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                    def gitCommitAuthor = sh(script: 'git log -1 --pretty=%an', returnStdout: true).trim()
                    params = [
                        url: "${env.BUILD_URL}",
                        gitUrl: "${env.GIT_URL}",
                        gitBranch: "${env.GIT_BRANCH}",
                        gitCommitMessage: gitCommitMessage,
                        gitCommitId: "${env.GIT_COMMIT}",
                        gitCommitAuthor: gitCommitAuthor
                    ]
                }
            }
        }
        stage('Build') {
            steps {
                echo "This is Panasonic IoT pipeline"
            }
        }
    }
    
    post {
        success {
            script {
                // Correctly assign the message variable
                def message = "Build Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
                notifyTeams(params, message)
            }
        }
        failure {
            script {
                // Correctly assign the message variable
                def message = "Build Failure: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
                notifyTeams(params, message)
            }
        }
        always {
            echo 'Pipeline completed.'
        }
    }
}

// Define the notifyTeams function
def notifyTeams(Map<String, String> params, String message) {
    echo "Message: ${message}"
    echo "Git URL: ${params.gitUrl}"
    echo "Branch: ${params.gitBranch}"
    echo "Commit Message: ${params.gitCommitMessage}"
    echo "Commit ID: ${params.gitCommitId}"
    echo "Commit Author: ${params.gitCommitAuthor}"
}
