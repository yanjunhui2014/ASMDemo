package com.milo.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.task("a_myPluginTask") {
            doLast {
                System.out.println("hello this is MyPlugin")
            }
        }

    }

}