def calling(){

    sh "aws cloudformation deploy --template-file /tmp/website2.yaml --stack-name stacknew --parameter-overrides BucketName=vishcfnhost5"

}
