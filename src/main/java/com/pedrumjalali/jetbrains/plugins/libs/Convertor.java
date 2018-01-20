package com.pedrumjalali.jetbrains.plugins.libs;

import com.intellij.psi.impl.source.html.HtmlTagImpl;
import com.pedrumjalali.util.file.Reader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

//Converts a list of html include statements into php include statements
public class Convertor {
    private String prevJs;
    private List<HtmlIncludeStatement> htmlIncludeStatements;
    private String base;
    public Convertor(List<HtmlIncludeStatement> __htmlIncludeStatements, String __base){
        htmlIncludeStatements = __htmlIncludeStatements;
        prevJs = "";
        base = __base;
    }

    public String run() throws IOException {
        StringBuilder _output = new StringBuilder();
        _output.append(getText(base + "/plugins/before.txt"));
        for (int i =0 ;i < htmlIncludeStatements.size();i++){
            _output.append(htmlIncludeStatements.get(i).toString());
            _output.append("\n");
        }
        _output.append(getText(base + "/plugins/after.txt"));
        return _output.toString();
    }

    private String getText(String __path) throws IOException {
        Reader _reader = new Reader(__path);
        List<String> _before = _reader.readFile();
        StringBuilder _output = new StringBuilder();
        _output.append("");
        for (int i = 0 ;i < _before.size();i++){
            _output.append(_before.get(i)).append("\n");
        }
        return _output.toString();
    }




}
