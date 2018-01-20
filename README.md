# Html2Spring
A jetbrain plugin to convert the header section of an html file into a Spring index file

1. Add a config file inside the target project under /plugins  
2. Add the following lines:
    - html2spring.source: <relative path from project root to source html file>
    - html2spring.dest: <relative path from project root to output file>
3. Add a before.txt and after.txt  
4. The plugin will appear under the tools menu named "Convert"

The plugin will search for all lines that include external css and javascript files:
```html
 <script src="somescript.js"></script>  
 <link rel="stylesheet" type="text/css" href="somestyle.css"></link>
```
and will convert it to
 ```html
  <script th:src="@{somescript.js}"></script>  
  <link rel="stylesheet" type="text/css" th:href="@{somestyle.css}"></link>
 ```

It will add all content from before.txt before the first include statement and all content int after.txt after the last 
include statement.
  
