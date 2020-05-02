# UserNameLookup
Find accounts on different websites associated with a provided Username

People tend to use the same online alias, or username, with multiple different websites. If you know a username for one website this tool will allow you to find more websites that the username is associated with.

Note:
  This utility does not guarantee that a username exists or not on a website. The utility is constantly being updated for increased accuracy, but 100% certainty cannot be guaranteed.

# Usage

Download a working build from Releases

Put Lookup.jar and Websites.txt into the same directory

Execute the included Java program from the command line

java -jar Lookup.jar

You can include the username to lookup as a commandline argument, or the program will ask you to input the UserName during runtime.

Example:

java -jar Lookup.jar SomeName

Will proceed to scan through the websites included in Websites.txt to find SomeName

# Contributing

If you want to add more websites follow the format in Websites.txt

# ToDo:  
 - Better error checking to see if an account really exists.
