def call(def nodeToolName){
    env.NODEJS_HOME = "${tool nodeToolName}"
    env.PATH="${env.NODEJS_HOME}/bin:${env.PATH}"
    env.npm_config_cache="npm-cache"
    sh 'printenv'
    sh "npm install"
}