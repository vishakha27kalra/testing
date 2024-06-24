// vars/mygroovy.groovy
def call(String stackName) {
    def templateFile = '/tmp/website2.yaml'
    def parameters = [
        'BucketName': 'vishcfnhost5'
    ]

    // Execute CloudFormation deployment
    deploy(templateFile, stackName, parameters)
}

def deploy(String templateFile, String stackName, Map<String, String> parameters) {
    def awsCliCmd = "aws cloudformation deploy"
    def parameterOverrides = parameters.collect { key, value -> "--parameter-overrides ${key}=${value}" }.join(" ")

    // Construct the full command
    def command = "${awsCliCmd} --template-file ${templateFile} --stack-name ${stackName} ${parameterOverrides}"

    // Execute the command
    def proc = command.execute()
    proc.waitFor()

    if (proc.exitValue() != 0) {
        error "Failed to deploy CloudFormation stack. Exit code: ${proc.exitValue()}"
    } else {
        echo "CloudFormation stack deployed successfully"
    }
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

