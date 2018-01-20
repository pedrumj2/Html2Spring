package com.pedrumjalali.jetbrains.plugins;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.pedrumjalali.jetbrains.plugins.libs.Convertor;
import com.pedrumjalali.jetbrains.plugins.libs.Visitor;
import com.pedrumjalali.util.Config;
import com.pedrumjalali.util.file.Writer;

import java.io.File;
import java.io.IOException;

public class HtmlToSpringAction extends AnAction {
    private String source;
    private String dest;
    private Project project;
    private String base;

    public HtmlToSpringAction() throws IOException {
        super("_Convertor");
    }

    private void init(AnActionEvent __event){
        project = __event.getProject();
        assert project != null;
        base = project.getBasePath();
        Config _config = new Config("config.txt", base + "/plugins");
        source = base + _config.getValue("html2spring.source");
        dest = base +   _config.getValue("html2spring.dest");
    }

    private PsiFile getSourceFile(){
        VirtualFile _vf =  LocalFileSystem.getInstance().findFileByIoFile(
                new File( source));
        PsiManager _psiManager = PsiManager.getInstance(project);
        return _psiManager.findFile(_vf);
    }

    public void actionPerformed(AnActionEvent __event) {
        init(__event);
        PsiFile _psiFile= getSourceFile();
        Visitor _visitor = new Visitor();
        _psiFile.accept(_visitor);
        Convertor _convertor = new Convertor(_visitor.extSourceStatements, base);
        Writer _writer = new Writer(dest);
        try {
            _writer.write(_convertor.run());
        } catch (IOException __e) {
            __e.printStackTrace();
        }
        _writer.close();
    }

}