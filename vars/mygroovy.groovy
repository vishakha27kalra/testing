// vars/mygroovy.groovy
def call() {
    def templateFile = '/tmp/website2.yaml'
    def stackName = 'stacknew'
    def parameters = [
        'BucketName': 'vishcfnhost5'
    ]

    // Execute CloudFormation deployment
    deploy(templateFile, stackName, parameters)
}
def deploy(String templateFile, String stackName, Map<String, String> parameters) {
    def awsCliCmd = "aws"
    def awsCliArgs = [
        "cloudformation",
        "deploy",
        "--template-file", templateFile,
        "--stack-name", stackName
    ]

    parameters.each { key, value ->
        awsCliArgs.addAll(["--parameter-overrides", "${key}=${value}"])
    }

    def command = [awsCliCmd] + awsCliArgs
    def proc = command.execute()
    proc.waitFor()

    if (proc.exitValue() != 0) {
        error "Failed to deploy CloudFormation stack. Exit code: ${proc.exitValue()}"
    } else {
        println "CloudFormation stack deployed successfully"
    }
}

