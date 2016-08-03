package com.puke;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.lang.model.element.Modifier;

/**
 * @author zijiao
 * @version 16/8/3
 */
public class JavaPoetTest {

    public static void create() throws IOException {
        final String packageName = "com.puke.test";
        final String className = "Hello";

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
        javaFile.writeTo(new File("/Users/jiao/Desktop/studioWorkSpace/APTDeom/apttest/src/main/java"));
    }

}
