package com.puke;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.Serializable;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;

/**
 * @author zijiao
 * @version 16/8/3
 */
public class GenerateHelper {

    private Filer filer;
    private String name;

    public GenerateHelper(Filer filer, String name) {
        if (name == null || name.length() <= 0) {
            throw new RuntimeException("the name is null");
        }
        this.filer = filer;
        this.name = name;
    }

    public void generate() {
        final String packageName = "com.puke.test";
        String start = name.substring(0, 1).toUpperCase();
        String fix = name.length() == 1 ? "" : name.substring(1, name.length());
        final String className = start + fix;

        //field
        FieldSpec fieldSpec = FieldSpec.builder(String.class, "name")
                .addModifiers(Modifier.PRIVATE)
                .build();

        //constructor
        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class, "name")
                .addCode("this.name = name;")
                .build();

        //getMethod
        MethodSpec getMethod = MethodSpec.methodBuilder("getName")
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addCode("return this.name;")
                .build();

        //type
        TypeSpec typeSpec = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                .superclass(Object.class)
                .addSuperinterface(Serializable.class)
                .addField(fieldSpec)
                .addMethod(constructor)
                .addMethod(getMethod)
                .build();

        //file
        JavaFile javaFile = JavaFile.builder(packageName, typeSpec)
                .addFileComment("this is a test file")
                .build();
        try {
            javaFile.writeTo(filer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
