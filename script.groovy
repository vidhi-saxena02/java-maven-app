def buildApp() {
    echo 'building the application...'
} 

def testApp() {
    echo 'testing the application...'
} 

def deployApp() {
    echo 'deplying the application...'
    echo "deploying version ${params.VERSION}"
} 

def buildJar() {
    echo 'building the jar...'
}

def buildImage() {
    echo 'building the image...'
}

return this
