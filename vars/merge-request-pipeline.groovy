def call(Map body) {

    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    pipeline {
        agent any
        stages {
            stage('Checkout') {
                steps {
                    git url: pipelineParams.gitUrl
                }
            }

            stage('Deploy'){
                steps {
                    deploy("Hola Pipeline")
                }
            }
        }
    }
}