void call(def serverlessCredentialsId, def serverlessStage){
    
    env.AWS_DEFAULT_REGION = 'us-east-2'
    withCredentials(
    [[
        $class: 'AmazonWebServicesCredentialsBinding',
        credentialsId: serverlessCredentialsId,
        accessKeyVariable: 'AWS_ACCESS_KEY_ID',
        secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
    ]]) {
        sh "serverless config credentials --provider aws --key ${AWS_ACCESS_KEY_ID} --secret ${AWS_SECRET_ACCESS_KEY} --profile custom-profile --overwrite --stage=${stage}"
        sh "serverless deploy --aws-profile custom-profile --stage=${stage} --verbose"
    } 
}
