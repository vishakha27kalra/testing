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
                notifyGoogleChat(parameter, message)
            }
        }
        failure {
            script {
                // Correctly assign the message variable
                def message = "Build Failure: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
                notifyGoogleChat(parameter, message)
            }
        }
        always {
            echo 'Pipeline completed.'
        }
    }
}


def notifyGoogleChat(parameter, message) {
    def googleChatWebhookUrl = 'https://chat.googleapis.com/v1/spaces/AAAAKgneo2I/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=_LNdvwSZ08X6DqkQACKgJlry34nvEc4vVDu0xM1g7ZI'
    def payload = {
        "text": """[
            [
                "header": [
                    "title": message,
                    "subtitle": "Jenkins CI/CD Pipeline Notification",
                    "imageUrl": "https://www.jenkins.io/images/logos/jenkins/jenkins.png",
                    "imageStyle": "IMAGE"
                ],
                "sections": [
                    [
                        "widgets": [
                            [
                                "keyValue": [
                                    "topLabel": "Build URL",
                                    "content": parameter.url,
                                    "contentMultiline": true,
                                    "onClick": [
                                        "openLink": [
                                            "url": parameter.url
                                        ]
                                    ]
                                ]
                            ],
                            [
                                "keyValue": [
                                    "topLabel": "Git URL",
                                    "content": parameter.gitUrl,
                                    "contentMultiline": true
                                ]
                            ],
                            [
                                "keyValue": [
                                    "topLabel": "Branch",
                                    "content": parameter.gitBranch,
                                    "contentMultiline": true
                                ]
                            ],
                            [
                                "keyValue": [
                                    "topLabel": "Commit Message",
                                    "content": parameter.gitCommitMessage,
                                    "contentMultiline": true
                                ]
                            ],
                            [
                                "keyValue": [
                                    "topLabel": "Commit ID",
                                    "content": parameter.gitCommitId,
                                    "contentMultiline": true
                                ]
                            ],
                            [
                                "keyValue": [
                                    "topLabel": "Commit Author",
                                    "content": parameter.gitCommitAuthor,
                                    "contentMultiline": true
                                ]
                            ]
                        ]
                    ]
                ]
            ]
        ]"""
    }

    httpRequest httpMode: 'POST',
                acceptType: 'APPLICATION_JSON',
                contentType: 'APPLICATION_JSON',
                url: googleChatWebhookUrl,
                requestBody: groovy.json.JsonOutput.toJson(payload)
}