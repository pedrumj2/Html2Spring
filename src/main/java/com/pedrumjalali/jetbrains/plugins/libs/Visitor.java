package com.pedrumjalali.jetbrains.plugins.libs;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import com.intellij.psi.impl.source.html.HtmlTagImpl;

import java.util.ArrayList;
import java.util.List;

public class Visitor extends PsiRecursiveElementVisitor {
    public List<HtmlTagImpl> psiElements = new ArrayList<HtmlTagImpl>();
    public List<HtmlIncludeStatement> extSourceStatements = new ArrayList<>();

    @Override
    public void visitElement(PsiElement element) {
        if (element instanceof HtmlTagImpl){
            HtmlTagImpl _htmlTagIml =  (HtmlTagImpl)element;
            HtmlIncludeStatement _extSourceStatement = new HtmlIncludeStatement(_htmlTagIml);
            if (_extSourceStatement.type != HtmlIncludeStatement.Type.OTHER){
                extSourceStatements.add(_extSourceStatement);
            }
        }
        super.visitElement(element);
    }
}
