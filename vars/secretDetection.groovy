def call(){
    def text = libraryResource 'yelp/.pre-commit-config.yaml'
    writeFile file: "${WORKSPACE}/.pre-commit-config.yaml", text: text
    
    //sh 'cp /opt/scripts/jenkins/.pre-commit-config.yaml .'
    sh 'pre-commit run --all-files'
}