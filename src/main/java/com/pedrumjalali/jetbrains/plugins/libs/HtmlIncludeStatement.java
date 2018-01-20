package com.pedrumjalali.jetbrains.plugins.libs;

import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.html.HtmlTagImpl;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;

import java.nio.file.Path;
import java.nio.file.Paths;

public class HtmlIncludeStatement {
    public String source;
    public enum Type{
        JAVASCRIPT,
        CSS,
        THMETA,
        OTHER
    };
    public Type type;
    public HtmlTagImpl htmlTagImpl;
    public XmlAttribute[] xmlAttributes;
    public String name;
    public HtmlIncludeStatement(HtmlTagImpl __htmlTagIml){
        htmlTagImpl = __htmlTagIml;
        type = getType(htmlTagImpl);
        if (type != Type.OTHER){
            source = getSource(htmlTagImpl, type);
            name = getName(source);
        }


    }

    public HtmlIncludeStatement(String __source, Type __type){
        type = __type;
        source = __source;
        name = getName(source);
    }

    public String toString(){
        if (type == Type.JAVASCRIPT){
            return  toStringJs();
        }
        else{
            return toStringCss();
        }
    }

    private String toStringCss(){
        String _output = "<link rel=\"stylesheet\" type=\"text/css\" href=\"";
        _output += source;
        _output += "\"></link>";
        return  _output;
    }
    private String toStringJs(){
        String _output = "<script src=\"";
        _output+= source;
        _output+= "\"></script>";
        return _output;
    }

    private String getName(String __path){
        Path p = Paths.get(__path);
        return p.getFileName().toString();
    }

    private String getSource(HtmlTagImpl __htmlTag, Type __type){
        if (__type == Type.JAVASCRIPT){
            return getSourceJavascript(__htmlTag);
        }
        else{
            return getSourceCss(__htmlTag);
        }
    }

    private String getSourceJavascript(HtmlTagImpl __htmlTagImpl){
        XmlAttribute  _srcAttribute = __htmlTagImpl.getAttribute("th:src");
        PsiElement[] _elements =  _srcAttribute.getChildren();
        XmlAttributeValue _value = (XmlAttributeValue)_elements[2];
        return _value.getValue().substring(2, _value.getValue().length()-1);
    }

    private String getSourceCss(HtmlTagImpl __htmlTagImpl){
        XmlAttribute  _srcAttribute = __htmlTagImpl.getAttribute("th:href");
        PsiElement[] _elements =  _srcAttribute.getChildren();
        XmlAttributeValue _value = (XmlAttributeValue)_elements[2];
        return _value.getValue().substring(2, _value.getValue().length()-1);
    }

    private Type getType(HtmlTagImpl __htmlTagImpl){
        if (isJavaScript(__htmlTagImpl)){
            return  Type.JAVASCRIPT;
        }
        else if (isCss(__htmlTagImpl)){
            return  Type.CSS;
        }
        else{
            return Type.OTHER;
        }
    }

    public boolean isJavaScript(HtmlTagImpl __htmlTagImpl){
        return __htmlTagImpl.getName().equals("script");
    }
    public boolean isCss(HtmlTagImpl __htmlTagImpl){
        return __htmlTagImpl.getName().equals("link");
    }


}
