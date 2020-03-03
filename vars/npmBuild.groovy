def call(def nodeToolName, def vaultPath, def vaultStage){
    env.NODEJS_HOME = "${tool nodeToolName}"
    env.PATH="${env.NODEJS_HOME}/bin:${env.PATH}"
    env.npm_config_cache = 'npm-cache'
    sh "bash /opt/scripts/get-config.sh ${vaultPath} ${vaultStage}"
    sh 'npm run build'
}