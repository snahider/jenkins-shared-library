def call(){
    def configFileName = 'pipeline-config.yaml'
    def repositoryConfig = libraryResource configFileName
    //writeFile file: "${WORKSPACE}/${configFileName}", text: text
    Map pipelineCfg = readYaml(text: repositoryConfig)
    echo "Repository Pipeline Configuration\n$pipelineCfg"
    return pipelineCfg
}