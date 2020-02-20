def call(def gitUrl){
    def pipelineConfig = pipelineConfig()

    checkout changelog: true, scm: [
        $class: 'GitSCM',
        userRemoteConfigs: [[credentialsId: pipelineConfig.gitCredentialsId , url: gitUrl]],
        branches: [[name: "origin/${env.gitlabSourceBranch}"]],
        extensions: [[$class: 'PreBuildMerge', options: [fastForwardMode: 'FF', mergeRemote: 'origin', mergeStrategy: 'DEFAULT', mergeTarget: "${env.gitlabTargetBranch}"]]]
    ]
}