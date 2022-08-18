package com.milo.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

/**
 * Title：
 * Describe：
 * Remark：
 * <p>
 * Created by Milo
 * E-Mail : 303767416@qq.com
 * 2022/8/18
 */
class MyPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.task("a_myPluginTask") {
            it.doFirst {
                System.out.println("Hello this is MyPlugin")

                val minifyReleaseWithR8: Task = project.tasks.findByName("minifyReleaseWithR8") as Task
                if (minifyReleaseWithR8 == null) {
                    System.out.println("minifyReleaseWithR8 == null")
                    val transform = CompatSensorTransform()
                } else {
                    System.out.println("minifyReleaseWithR8 has found")
                }

            }
        }
    }

}