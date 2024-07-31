// pipeline {
//     agent any
//     stages {
//         stage('Checkout') {
//             steps {
//                 checkout scm
//                 script {
//                     echo "Checked out branch: ${env.GIT_BRANCH}"
//                     echo "Commit SHA: ${env.GIT_COMMIT}"
//                 }
//             }
//         }
//     }
// }
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
                script {
                    // Fetching in-built variables after checkout
                    def gitUrl = env.GIT_URL
                    def gitCommitId = env.GIT_COMMIT
                    def gitBranch = env.GIT_BRANCH
                    def gitCommitMessage = env.GIT_MESSAGE ?: 'No commit message available'
                    def gitCommitAuthor = env.GIT_COMMITTER_NAME ?: 'Unknown Author'

                    // Echoing the values
                    echo "Git URL: ${gitUrl}"
                    echo "Commit ID: ${gitCommitId}"
                    echo "Branch: ${gitBranch}"
                    echo "Commit Message: ${gitCommitMessage}"
                    echo "Commit Author: ${gitCommitAuthor}"
                }
            }
        }
    }
}
