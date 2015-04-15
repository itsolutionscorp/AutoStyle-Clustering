# Exercise "Bob" for Python
# Written from skeleton file on October 20th 2014
# By Falconhaxx
def hey(what):
    if what.strip() == "": #If the input string contains only whitespace
        return "Fine. Be that way!"
    elif what.isupper(): #If the input string contains letters and all letters are upper case
        return "Whoa, chill out!"
    elif what.strip().endswith("?"): #If the last character of the input string, with whitespace removed, is a question mark
        return "Sure."
    else:
        return "Whatever."
        
#V2 written on December 10th 2014
