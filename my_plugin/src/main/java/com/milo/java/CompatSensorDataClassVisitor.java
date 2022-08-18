package com.milo.java;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Title：
 * Describe：
 * Remark：
 * <p>
 * Created by Milo
 * E-Mail : 303767416@qq.com
 * 2022/8/18
 */
class CompatSensorDataClassVisitor extends ClassVisitor {
    private String mClassName;
    private String mSuperName;

    public CompatSensorDataClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.mClassName = name;
        this.mSuperName = superName;

        if ("com.milo.asmdemo".equals(mClassName)) {
            System.out.printf("成功匹配到%s%n", mClassName);
        }
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("ClassVisitor visitMethod name------" + name + ",superName is" + mSuperName);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        return mv;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
