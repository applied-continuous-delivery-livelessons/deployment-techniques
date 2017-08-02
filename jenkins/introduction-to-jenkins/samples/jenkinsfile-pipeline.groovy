// execute this job in a worker with any label
// you could call node("label") to run it on a node with label "label"
node("") {
    // stage is a series of steps
    stage("compile") {
        git('https://github.com/marcingrzejszczak/simple-maven-boot.git')
        sh("./mvnw clean compile")
    }
    stage("build") {
        sh("./mvnw clean install")
    }
    stage('Results') {
        junit('**/target/surefire-reports/TEST-*.xml')
        archive('target/*.jar')
    }
}
