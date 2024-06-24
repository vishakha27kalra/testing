// vars/deployCloudFormation.groovy

def call(String templateFile, String stackName, Map<String, String> parameters) {
    def awsCliCmd = "aws"
    def awsCliArgs = [
        "cloudformation",
        "deploy",
        "--template-file", templateFile,
        "--stack-name", stackName
    ]
    parameters.each { key, value ->
        awsCliArgs += "--parameter-overrides", "${key}=${value}"
    }
    def command = [awsCliCmd] + awsCliArgs
    def proc = command.execute()
    proc.waitFor()
    if (proc.exitValue() != 0) {
        error "Failed to deploy CloudFormation stack. Exit code: ${proc.exitValue()}"
    } else {
        echo "CloudFormation stack deployed successfully"
    }
}
