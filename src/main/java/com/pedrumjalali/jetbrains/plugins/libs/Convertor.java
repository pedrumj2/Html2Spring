package com.pedrumjalali.jetbrains.plugins.libs;

import com.intellij.psi.impl.source.html.HtmlTagImpl;

import java.io.BufferedWriter;
import java.util.List;

//Converts a list of html include statements into php include statements
public class Convertor {
    private String prevJs;
    private List<HtmlIncludeStatement> htmlIncludeStatements;
    
    public Convertor(List<HtmlIncludeStatement> __htmlIncludeStatements){
        htmlIncludeStatements = __htmlIncludeStatements;
        prevJs = "";
    }

    
    public String run(){

        StringBuilder _output = new StringBuilder();

        for (int i =0 ;i < htmlIncludeStatements.size();i++){
            _output.append(htmlIncludeStatements.get(i).toString());
            _output.append("\n");
        }
        return _output.toString();
    }

    private String getBefore(){

    }




}
