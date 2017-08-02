// execute this job in a worker with any label
// you could call node("label") to run it on a node with label "label"
node("") {
    // stage is a series of steps
    stage("echo") {
        echo 'Hello World'
    }
}
