package com.milo.plugin

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import groovy.io.FileType

class CompatSensorTransform extends Transform {

    @Override
    String getName() {
        return "CompatSensorTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.PROJECT_ONLY
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)

        Collection<TransformInput> transformInputs = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider

        transformInputs.each { TransformInput transformInput ->
            //directoryInputs代表着以源码方式参与项目编译的所有目录结构及其目录下的源码文件
            //比如我们手写的类以及R.class,BuildConfig.class以及MainActivity.class，
            // Gradle3.6以后R文件打包成R.jar文件了
            transformInput.directoryInputs.each { DirectoryInput directoryInput ->
                handleDirectoryInput(directoryInput)
            }

        }
    }

    void handleDirectoryInput(DirectoryInput directoryInput) {
        File dir = directoryInput.file
        if (dir) {
            dir.traverse(type: FileType.FILES, nameFilter: ~/.*\.class/) { File file ->
                System.out.println("find class:" + file.name)
//                //对class文件进行读取与解析
//                ClassReader classReader=new ClassReader(file.bytes)
//                //对class文件的写入
//                ClassWriter classWriter=new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS)
//                //访问class文件相应的内容，解析到某一个结构就会通知到ClassVisitor的相应方法
//                ClassVisitor classVisitor=new LifecycleClassVisitor(classWriter)
//                //依次调用ClassVisitor接口的各个方法
//                classReader.accept(classVisitor,ClassReader.SKIP_FRAMES)
//                //toByteArray方法会将最终修改的字节码以byte数组形式返回
//                byte[] bytes=classWriter.toByteArray()
//                //通过文件流写入方式覆盖掉原先的内容，实现class文件的改写
//                FileOutputStream outputStream=new FileOutputStream(file.path)
//                outputStream.write(bytes)
//                outputStream.close()
            }
        }
    }

}