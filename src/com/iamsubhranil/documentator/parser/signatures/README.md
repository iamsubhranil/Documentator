This folder is used for specify method signatures of various languages. In this folder, there are files with format `language_name.signature` which denotes the method signature for that specific language.
Here is some basic rules on creating such a file : 
1. The values in the file should be specified as `name=value` format, where the name `signature` must be specifed.
2. To make a value optional in the signature, add `_` before it
3. To specify a fixed value in the signature, place it under double quotes
4. To specify a value which is fixed an optional, place the optional operator first(`_`), and then the quotes
5. You can use indirect reference to specify a value, but only single indirection is allowed
6. To specify multiple values for a type, use pipe(`|`) operator
7. To place a type which can consist of any words, use `any_word`
8. By default, `return_type` and `method_name` are specified to be `any_word`
9. You must specify the argument format for a method using the following structure inside the `signature` declaration
```
param_start params param_end
```
and explicitly define `param_start` and `param_end`. Anything in between them will be considered as parameters. The program however, will automatically extract parameters on `,` inside `param_start` and `param_end` of a method.
10. Don't define `params` as optional, i.e. don't explicitly place `_` in front of it. The program will take care of it. You should however, make `param_start` and `param_end` optional, if it is so in your language.