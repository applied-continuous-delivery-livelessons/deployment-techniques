import javaposse.jobdsl.dsl.DslFactory

// delegate of the script, if you want code completion just set it to a variable
// of DslFactory type
DslFactory factory = this

// The simplest job is the FreestyleJob
factory.job("simple-maven-boot-build") {
    // Required by the Delivery Pipeline view
    deliveryPipelineConfiguration("Build")
    // Trigger when a push to github was made
    triggers { githubPush() }
    // SCM configuration
    scm { github("marcingrzejszczak/simple-maven-boot") }
    // Steps to be executed
    steps { shell("./mvnw clean compile") }
    // What should happen after completion of steps
    publishers {
        // automatic step
        // for manual use buildPipelineTrigger
        downstreamParameterized {
            trigger("simple-maven-boot-install") { triggerWithNoParameters() }
        }
    }
}

factory.job("simple-maven-boot-install") {
    // Required by the Delivery Pipeline view
    deliveryPipelineConfiguration("Install")
    // SCM configuration
    scm { github("marcingrzejszczak/simple-maven-boot") }
    // Steps to be executed
    steps { shell("./mvnw clean install") }
    // What should happen after completion of steps
    publishers {
        archiveJunit("**/target/surefire-reports/TEST-*.xml")
        archiveArtifacts('target/*.jar')
    }
}

factory.deliveryPipelineView("simple-maven-boot-pipeline") {
    pipelines {
        component("Deployment", "simple-maven-boot-build")
    }
    allowPipelineStart()
    showChangeLog()
    allowRebuild()
    showDescription()
    showPromotions()
    showTotalBuildTime()
}