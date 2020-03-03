void call(def s3CredentialsId, def s3Bucket, def cloudfrontKey){
    env.AWS_DEFAULT_REGION = 'us-east-2'

    withAWS(region:"${env.AWS_DEFAULT_REGION}",credentials:s3CredentialsId) {
        s3Delete(bucket: s3Bucket, path:'**/*')
        s3Upload(bucket: s3Bucket, workingDir:'build', includePathPattern:'**/*', acl:'PublicRead');
        cfInvalidate(distribution:cloudfrontKey, paths:['/*'])
    }
}
