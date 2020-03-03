def call(){
    def configFileName = 'pipeline-config.yaml'
    def repositoryConfig = libraryResource configFileName
    Map pipelineCfg = readYaml(text: repositoryConfig)
    echo "Repository Pipeline Configuration\n$pipelineCfg"
    return pipelineCfg
}