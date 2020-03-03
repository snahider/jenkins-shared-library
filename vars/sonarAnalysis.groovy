/* Sonar Properties

Lambda Domain
--------------
sonar.projectKey=journeyvida-adn-domain
sonar.projectName=journeyvida-adn-domain
sonar.projectVersion=1.0
sonar.sources=.
sonar.sourceEncoding=UTF-8
sonar.javascript.file.suffixes=.js,.jsx
sonar.exclusions=node_modules/**,npm-cache/**,**/node_modules/**

Reac Web
-------------

sonar.projectKey=journey-vida-web
sonar.projectName=journey-vida-web
sonar.projectVersion=1.0
sonar.sources=src, public
sonar.sourceEncoding=UTF-8
sonar.javascript.file.suffixes=.js,.jsx
sonar.exclusions=node_modules/**,npm-cache/**,**/node_modules/**

/*

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