# JSON

## Notes

- name/value pairs (aka, "key", "value" pairs)
   - example: `{ "name": "value" }`
   - separated by commas, though trailing commas are not allowed
   - the name and values must be quoted with double-quotes
   - the name *may* contain spaces and special characters,
     but this is not recommended
       - underscores are recommended over spaces or dashes
   - names are always strings

- curly braces contain JSON objects (name/value pairs)

- square braces contain arrays (multiple JSON objects)

- names are always strings

- values can be one of six different data types
    1. strings (in quotes)
    1. numbers (integer or floating point)
    1. booleans (true or false)
    1. arrays
    1. JSON objects (name/value pairs)
    1. null
 
 - Frequently used with AJAX
 
 - Faster to process, much lighter than XML
 
 - Supported by major browsers and modern programming languages
 
 - Unline data in a database, structured data doesn't fit into a table.
   Instead, it is more like a tree which JSON expresses well.
   
 ## Useful tools
 
 - jsonlint.com
 - jsoneditoronline.org
     - provides a split panel, one side code and the other a parsed tree view
 
 ## References
 
 - json.org
 
