package com.milo.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        System.out.println("Hello this is MyPlugin")

        AppExtension android  = project.getExtensions().getByType(AppExtension.class)
        android.registerTransform(new CompatSensorTransform())
    }

}