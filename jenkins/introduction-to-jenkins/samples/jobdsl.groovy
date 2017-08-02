import javaposse.jobdsl.dsl.DslFactory

// delegate of the script, if you want code completion just set it to a variable
// of DslFactory type
DslFactory factory = this

// The simplest job is the FreestyleJob
factory.job("job-dsl-simple-job") {
    steps {
        shell("echo 'hello'")
    }
}