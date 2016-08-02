package com.puke;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
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
        Util.println("注解个数: " + annotations.size());
        for (TypeElement element : annotations) {
            PK pk;
            if (element == null || (pk = element.getAnnotation(PK.class)) == null) {
                continue;
            }
            Util.println("收到打印信息: " + pk.value());
        }
        return false;
    }

}
