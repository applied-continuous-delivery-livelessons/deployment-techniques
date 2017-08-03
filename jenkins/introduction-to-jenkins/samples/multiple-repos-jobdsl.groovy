import javaposse.jobdsl.dsl.DslFactory

// delegate of the script, if you want code completion just set it to a variable
// of DslFactory type
DslFactory factory = this

// fetch repos from somewhere
List<String> repos = ["a", "b", "c", "d"]

// The simplest job is the FreestyleJob
repos.each {
    factory.job("${it}-job") {
        steps {
            shell("echo 'hello'")
        }
    }
}
