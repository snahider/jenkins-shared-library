def call(){
    def configFileName = 'pipeline-config.yaml'
    def config = libraryResource configFileName
    writeFile file: "${WORKSPACE}/${configFileName}", text: text

    Map pipelineCfg = readYaml(file: "${WORKSPACE}/${configFileName}")
    return pipelineCfg
}