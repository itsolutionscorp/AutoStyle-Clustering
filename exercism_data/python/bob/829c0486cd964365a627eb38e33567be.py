import re

def lastChar(word):
    if(len(word) > 0):
        return word[-1]
    else:
        return ""

def hey(what):
    alphaCount = len(re.findall('[a-z]',what.lower()))
    numericCount = len(re.findall('[0-9]',what))
    
    #Yell check (is all caps?)
    if(what.upper() == what and alphaCount > 0):
        return "Whoa, chill out!"
    #Question check (ends in question mark?)
    elif(lastChar(what) == '?'):
        return "Sure."
    #Null check (Is nothing said?)
    elif(alphaCount == 0 and numericCount == 0):
        return "Fine. Be that way!"
    else:
        return "Whatever."
