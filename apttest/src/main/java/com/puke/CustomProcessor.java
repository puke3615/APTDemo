package com.puke;

import com.squareup.javapoet.JavaFile;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaCompiler;

/**
 * @author zijiao
 * @version 16/8/1
 */
@SupportedAnnotationTypes({
        "com.puke.PK"
})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class CustomProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        Util.println("执行初始化asdasdad操作..");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(PK.class)) {
            PK pk;
            if (element == null
                    || (pk = element.getAnnotation(PK.class)) == null
                    || element.getKind() == ElementKind.CLASS) {
                continue;
            }
            Util.println("收到打印信息: " + pk.value());
        }
        return false;
    }

}
