import javaposse.jobdsl.dsl.DslFactory

DslFactory factory = this

factory.job('seed-job') {
    triggers { githubPush() }
    scm { github("applied-continuous-delivery-livelessons/continuous-integration") }
    wrappers { colorizeOutput() }
    steps {
        shell("./mvnw clean install")
        dsl {
            external('jenkins/introduction-to-jenkins/samples/jobdsl-pipeline.groovy')
            removeAction('DISABLE')
            removeViewAction('DELETE')
            ignoreExisting(false)
        }
    }
}