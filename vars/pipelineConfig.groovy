def call(){
    def configFileName = 'pipeline-config.yaml'
    def repositoryConfig = libraryResource configFileName
    //writeFile file: "${WORKSPACE}/${configFileName}", text: text
    echo 'Repository Pipeline Configuration'
    echo "$repositoryConfig"

    Map pipelineCfg = readYaml(text: repositoryConfig)
    return pipelineCfg
}