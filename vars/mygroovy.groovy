// vars/deployCloudFormation.groovy

def call(String templateFile, String stackName, Map<String, String> parameters) {
    // AWS CLI command components
    def awsCliCmd = "aws"
    def awsCliArgs = [
        "cloudformation",
        "deploy",
        "--template-file", templateFile,
        "--stack-name", stackName
    ]

    // Adding parameter overrides
    parameters.each { key, value ->
        awsCliArgs += "--parameter-overrides", "${key}=${value}"
    }

    // Constructing the command string
    def command = [awsCliCmd] + awsCliArgs

    // Execute the command
    def proc = command.execute()
    proc.waitFor()

    // Handling command execution result
    if (proc.exitValue() != 0) {
        error "Failed to deploy CloudFormation stack. Exit code: ${proc.exitValue()}"
    } else {
        echo "CloudFormation stack deployed successfully"
    }
}
