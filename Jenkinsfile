 Map<String, String> parameter =[:]
 pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                script {
                    parameter = checkout scm
                    def gitCommitMessage = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                    def gitCommitAuthor = sh(script: 'git log -1 --pretty=%an', returnStdout: true).trim()
                    gitCommitId = parameter.get("GIT_COMMIT")
                    gitUrl = parameter.get("GIT_URL")
                    gitBranch = parameter.get("GIT_BRANCH")
                    parameter.gitCommitAuthor = gitCommitAuthor
                    parameter.gitCommitMessage = gitCommitMessage
                    url = env.BUILD_URL
                    //gitCommitMessage = gitCommitMessage
                }
                // Use the credentials defined in Jenkins
                //checkout scm
                // script {
                //     parameter = checkout scm
                //     //println parameter
                //     gitCommitId = parameter.get("GIT_COMMIT")
                //     gitUrl = parameter.get("GIT_URL")
                //     gitBranch = parameter.get("GIT_BRANCH")
                //     gitCommitAuthor = parameter.get("GIT_AUTHOR_NAME")
                //     url = env.BUILD_URL
                //     gitCommitMessage = parameter.get("GIT_COMMIT_MESSAGE")
                //     echo "hihihihi"
                //     println parameter
                    
                //     // println gitCommitId
                //     // println url
                //     // println gitCommitMessage
                //     // println gitCommitAuthor
                //     // Fetching in-built variables after checkout
                    
                //     //def gitCommitMessage = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                //     //def gitCommitAuthor = sh(script: 'git log -1 --pretty=%an', returnStdout: true).trim()
                //     // params = [
                //     //     url: "${env.BUILD_URL}",
                //     //     gitUrl: "${env.GIT_URL}",
                //     //     gitBranch: "${env.GIT_BRANCH}",
                //     //     gitCommitMessage: "${env.GIT_COMMIT_MESSAGE}",
                //     //     gitCommitId: "${env.GIT_COMMIT}",
                //     //     gitCommitAuthor: "${env.GIT_AUTHOR_NAME}"
                //     // ]
                //     // sh "env"
                // }
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
                notifyTeams(parameter, message)
            }
        }
        failure {
            script {
                // Correctly assign the message variable
                def message = "Build Failure: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
                notifyTeams(parameter, message)
            }
        }
        always {
            echo 'Pipeline completed.'
        }
    }
}

//Define the notifyTeams function
def notifyTeams(Map<String, String> parameter, String message) {
    echo "Message: ${message}"
    echo "Git URL: ${gitUrl}"
    echo "Branch: ${gitBranch}"
    echo "Commit Message: ${parameter.gitCommitMessage}"
    echo "Commit ID: ${gitCommitId}"
    echo "Commit Author: ${parameter.gitCommitAuthor}"
    echo "build url: ${url}"
}
