/*
mergeRequestPipeline {
    gitUrl = 'https://github.com/snahider/react-example.git'
}
*/

def call(body) {
    
    def pipelineConfig = pipelineConfig()
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    pipelineConfig << pipelineParams

    pipeline {
        agent any

        // options {
        //     gitLabConnection(pipelineConfig.gitlabInternalCE)
        //     gitlabBuilds(builds: ['Checkout','Install Dependencies','Secret Detection','Static Code Analysis'])
        // }

        stages {
            stage('Checkout') {
                steps {
                    //gitlabCommitStatus('Checkout'){
                        //gitCheckoutWithMerge (pipelineParams.gitUrl)
                        git pipelineParams.gitUrl
                    //}
                }
            }

            stage('Install Dependencies') {
                steps {
                    //gitlabCommitStatus('Install Dependencies'){
                        npmInstall(pipelineConfig.nodeToolName)
                    //}
                }
            }

            stage('Secret Detection') {
                steps {
                    //gitlabCommitStatus('Secret Detection'){
                        secretDetection()
                    //}
                }
            }

            stage('Static Code Analysis') {
                steps {
                    //gitlabCommitStatus('Static Code Analysis'){
                        sonarAnalysis()
                    //}
                }
            }
        }
    }
}