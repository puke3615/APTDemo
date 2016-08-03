package com.puke;

import com.squareup.javapoet.JavaFile;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
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

    private Messager messager;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        info("=======开始process了=======");
        for (Element element : roundEnv.getElementsAnnotatedWith(PK.class)) {
            PK pk;
            if (element == null
                    || (pk = element.getAnnotation(PK.class)) == null
                    || element.getKind() != ElementKind.CLASS) {
                continue;
            }

            info("准备生成代码了");
            String name = pk.value();
            new GenerateHelper(filer, name).generate();

        }
        info("=======结束process了=======");
        return false;
    }

    private void info(String format, Object... args) {
        messager.printMessage(Diagnostic.Kind.NOTE, String.format(format, args));
    }

    private void error(String format, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(format, args));
    }


}
