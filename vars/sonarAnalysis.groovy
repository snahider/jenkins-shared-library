void call(){
    def pipelineConfig = pipelineConfigYaml()
    
    scannerHome = tool pipelineConfig.sonarScannerToolName
    withSonarQubeEnv(credentialsId: pipelineConfig.sonarCredentialsId,
                     installationName: pipelineConfig.sonarInstallationName) {
        sh "${scannerHome}/bin/sonar-scanner"
    }

    timeout(time: 5, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: true
    }
}