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
                def params = [
                    message: "Build Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    url: "${env.BUILD_URL}",
                    gitUrl: "${env.GIT_URL}",
                    gitBranch: "${env.GIT_BRANCH}",
                    gitCommitMessage: env.GIT_COMMIT_MESSAGE,
                    gitCommitId: "${env.GIT_COMMIT}",
                    gitCommitAuthor: env.GIT_COMMIT_AUTHOR
                ]
                params.each { key, value ->
                    echo "${key}: ${value}"
                }
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
def notifyTeams(Map<String, String> params){
    echo "params.message"
}
