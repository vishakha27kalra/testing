 pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Use the credentials defined in Jenkins
                checkout scm
                script {
                    Map<String, String> parameter = checkout scm
                    println parameter
                    gitCommitId = parameter.get("GIT_COMMIT");
                    gitUrl = parameter.get("GIT_URL")
                    gitBranch = parameter.get("GIT_BRANCH")
                    println gitCommitId;
                    println gitUrl;
                    println gitBranch;
                    // Fetching in-built variables after checkout
                    
                    //def gitCommitMessage = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                    //def gitCommitAuthor = sh(script: 'git log -1 --pretty=%an', returnStdout: true).trim()
                    // params = [
                    //     url: "${env.BUILD_URL}",
                    //     gitUrl: "${env.GIT_URL}",
                    //     gitBranch: "${env.GIT_BRANCH}",
                    //     gitCommitMessage: "${env.GIT_COMMIT_MESSAGE}",
                    //     gitCommitId: "${env.GIT_COMMIT}",
                    //     gitCommitAuthor: "${env.GIT_AUTHOR_NAME}"
                    // ]
                    // sh "env"
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
                //notifyTeams(parameter, message)
            }
        }
        failure {
            script {
                // Correctly assign the message variable
                def message = "Build Failure: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
                //notifyTeams(params, message)
            }
        }
        always {
            echo 'Pipeline completed.'
        }
    }
}

// Define the notifyTeams function
// def notifyTeams(Map<String, String> params, String message) {
//     echo "Message: ${message}"
//     echo "Git URL: ${params.gitUrl}"
//     echo "Branch: ${params.gitBranch}"
//     echo "Commit Message: ${params.gitCommitMessage}"
//     echo "Commit ID: ${params.gitCommitId}"
//     echo "Commit Author: ${params.gitCommitAuthor}"
// }
