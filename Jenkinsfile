pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
                script {
                    echo "Checked out branch: ${env.GIT_BRANCH}"
                    echo "Commit SHA: ${env.GIT_COMMIT}"
                }
            }
        }
    }
}