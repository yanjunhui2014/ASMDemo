package com.milo.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager

/**
 * Title：
 * Describe：
 * Remark：
 * <p>
 * Created by Milo
 * E-Mail : 303767416@qq.com
 * 2022/8/18
 */
class CompatSensorTransform : Transform() {
    override fun getName(): String {
        return "CompatSensorTransform"
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.PROJECT_ONLY
    }

    override fun isIncremental(): Boolean {
        return false
    }

    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)

        var transformInputs: Collection<TransformInput> = transformInvocation!!.inputs
        var outputProvider: TransformOutputProvider = transformInvocation.outputProvider

        transformInputs.forEach {

            it.jarInputs.forEach { jar ->
                printJarFileName(jar)
            }

        }

    }

    private fun printJarFileName(jarInput: JarInput) {
        System.out.println("find jar file:" + jarInput.file.name)
    }

}