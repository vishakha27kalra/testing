pipeline{
    agent any
    parameters {
        string(name: 'AWS_REGION', defaultValue: 'us-west-2', description: 'AWS region to use')
    }
    environment {
        AWS_CREDENTIALS = credentials('aws-key')
        AWS_ACCESS_KEY_ID = "${AWS_CREDENTIALS_USR}"
        AWS_SECRET_ACCESS_KEY = "${AWS_CREDENTIALS_PSW}"
        AWS_REGION = "${params.AWS_REGION}"
    }
    stages{
        stage(test){
            steps{
                echo "deploying static website using cfn and s3"
            }
        }
        stage(deploy){
            steps{
                script{
                    sh 'aws --version'
                    sh "aws s3 ls"
                    sh "rm -rf testing"
                    sh "git clone https://github.com/vishakha27kalra/testing.git"
                    sh "pwd"
                    sh "cd ./testing"
                    sh "ls -R testing" 
                    sh 'cp ./testing/website2.yaml /tmp/website2.yaml'
                    sh "aws cloudformation deploy --template-file /tmp/website2.yaml --stack-name stacknew --parameter-overrides BucketName=vishcfnhost4"

                }
            }
        }
    }
}