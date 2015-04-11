# Exercise "Bob" for Python
# Written from skeleton file on October 20th 2014
# By Falconhaxx
def hey(what):
    what_upper = what.upper() #Upper case version of the input string
    what_clean = " ".join(what.split()) #Input string with whitespace removed
    what_has_alpha = any(character.isalpha() for character in what) # Boolean to check if the input string has any letters at all
    if what_clean == "": #If the input string contains only whitespace
        return "Fine. Be that way!"
    elif what_has_alpha and what_upper == what: #If the input string contains letters and all letters are upper case
        return "Whoa, chill out!"
    elif what_clean[len(what_clean)-1] == "?": #If the last character of the input string, with whitespace removed, is a question mark
        return "Sure."
    else:
        return "Whatever."
        
