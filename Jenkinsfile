 Map<String, String> parameter =[:]
 pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                script {
                    parameter = checkout scm
                    def gitCommitMessage = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                    gitCommitId = parameter.get("GIT_COMMIT")
                    gitUrl = parameter.get("GIT_URL")
                    gitBranch = parameter.get("GIT_BRANCH")
                    gitCommitAuthor = parameter.get("GIT_AUTHOR_NAME")
                    url = env.BUILD_URL
                    parameter.gitCommitMessage = gitCommitMessage
                    sh "env"
                    println parameter 
                }
                
                
            }
        }
        stage('Build') {
            steps {
                script {
                    def jobUserId, jobUserName
                    wrap([$class: 'BuildUser']) {
                        jobUserId = "${BUILD_USER_ID}"
                        jobUserName = "${BUILD_USER}"
                    }
                    if (env.BRANCH_NAME == 'master' && "${jobUserId}" =~ /devops/) {
                        echo "Building branch ${env.BRANCH_NAME} by user ${jobUserId}"
                        // Your build steps here
                    } else {
                        echo "Pipeline execution not authorized for user ${jobUserId} on branch ${env.BRANCH_NAME}"
                    }
                }
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
    def googleChatWebhookUrl = 'https://chat.googleapis.com/v1/spaces/AAAAtJ_EhFA/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=HhGCI4lxhCF7f7j1sgF7cj8f1T1OMSZoZ_AmnW7ORBo'
    
    def payload = [
        "cards": [
            [
                "header": [
                    "title": message,
                    "subtitle": "Jenkins CI/CD Pipeline Notification",
                    "imageUrl": "https://www.jenkins.io/images/logos/jenkins/jenkins.png"
                ],
                "sections": [
                    [
                        "widgets": [
                            [
                                "keyValue": [
                                    "topLabel": "Build URL",
                                    "content": url,
                                    "onClick": [
                                        "openLink": [
                                            "url": url
                                        ]
                                    ]
                                ]
                            ],
                            [
                                "keyValue": [
                                    "topLabel": "Git URL",
                                    "content": gitUrl
                                ]
                            ],
                            [
                                "keyValue": [
                                    "topLabel": "Branch",
                                    "content": gitBranch
                                ]
                            ],
                            [
                                "keyValue": [
                                    "topLabel": "Commit Message",
                                    "content": parameter.gitCommitMessage
                                ]
                            ],
                            [
                                "keyValue": [
                                    "topLabel": "Commit ID",
                                    "content": gitCommitId
                                ]
                            ],
                            [
                                "keyValue": [
                                    "topLabel": "Commit Author",
                                    "content": gitCommitAuthor
                                ]
                            ]
                        ]
                    ]
                ]
            ]
        ]
    ]
    
    // Print the payload for debugging
    println(groovy.json.JsonOutput.toJson(payload))

    httpRequest httpMode: 'POST',
                acceptType: 'APPLICATION_JSON',
                contentType: 'APPLICATION_JSON',
                url: googleChatWebhookUrl,
                requestBody: groovy.json.JsonOutput.toJson(payload)
}
