/*
Cómo usar el pipeline base:

    mergeRequestPipeline {
        gitUrl = 'https://github.com/snahider/react-example.git'
    }
*/

def call(body) {
    
    def pipelineConfig = pipelineConfigYaml()
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    echo "Parameters Pipeline Configuration\n$pipelineParams"
    def mergedConfig=pipelineConfig << pipelineParams
    echo "Merged Pipeline Configuration\n$mergedConfig"

    pipeline {
        agent any

        options {
            gitLabConnection(pipelineConfig.gitlabInternalCE)
            gitlabBuilds(builds: ['Checkout','Install Dependencies','Secret Detection','Static Code Analysis'])
        }

        stages {
            stage('Checkout') {
                steps {
                    gitlabCommitStatus('Checkout'){
                        gitCheckoutWithMerge (pipelineParams.gitUrl)
                        //git mergedConfig.gitUrl
                    }
                }
            }

            stage('Install Dependencies') {
                steps {
                    gitlabCommitStatus('Install Dependencies'){
                        npmInstall(mergedConfig.nodeToolName)
                    }
                }
            }

            stage('Secret Detection') {
                steps {
                    gitlabCommitStatus('Secret Detection'){
                        secretDetection()
                        echo 'secretDetection'
                    }
                }
            }

            stage('Static Code Analysis') {
                steps {
                    gitlabCommitStatus('Static Code Analysis'){
                        sonarAnalysis()
                    }
                }
            }
        }
    }
}