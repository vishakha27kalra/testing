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
                    
                    def gitCommitMessage = env.GIT_MESSAGE ?: 'No commit message available'
                    def gitCommitAuthor = env.GIT_AUTHOR_NAME ?: 'Unknown Author'
                    if (gitCommitMessage == 'No commit message available' || gitCommitAuthor == 'Unknown Author') {
                        gitCommitMessage = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                        gitCommitAuthor = sh(script: 'git log -1 --pretty=%an', returnStdout: true).trim()
                    }
                    def params = [
                    url: "${env.BUILD_URL}",
                    gitUrl: "${env.GIT_URL}",
                    gitBranch: "${env.GIT_BRANCH}",
                    gitCommitMessage: gitCommitMessage,
                    gitCommitId: "${env.GIT_COMMIT}",
                    gitCommitAuthor: gitCommitAuthor
                    ]
                    params.each { key, value ->
                        echo "${key}: ${value}"
                    }

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
                message: "Build Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
                notifyTeams(params,message)
            }

        }
        failure {
            script {
                def params = [
                    message: "Build FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    url: "${env.BUILD_URL}",
                    gitUrl: "${env.GIT_URL}",
                    gitBranch: "${env.GIT_BRANCH}",
                    gitCommitId: "${env.GIT_COMMIT}",
                ]
                params.each { key, value ->
                    echo "${key}: ${value}"
                }
            }
        }
        always {
            echo 'Pipeline completed.'
        }
    }
}
def notifyTeams(Map<String, String> params, String message){
    echo "$params.message"
    echo "$params.gitUrl"
    echo "$params.gitBranch"
    echo "$params.gitCommitMessage"
    echo "$params.gitCommitId"
    echo "$params.gitCommitAuthor"
    
}
