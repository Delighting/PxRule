### Main function switch
#-dontshrink
-dontoptimize
-dontobfuscate
-dontpreverify


### General options
-dontskipnonpubliclibraryclassmembers
-forceprocessing
-optimizations !code/simplification/arithmetic,!code/simplification/cast
-useuniqueclassmembernames
# Make the stack traces can still be deciphered later on
-keepattributes SourceFile, LineNumberTable
