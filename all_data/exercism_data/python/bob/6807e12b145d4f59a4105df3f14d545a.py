#
# Trade offs with this exercise was readability vs. performance. There is some duplication in checking values according to requirements, but the gain in readability makes a more maintainable solution.
#
import re

def hasAlpha(s):
    return re.match(r'^.+[A-Za-z]+',s)

def isAllSpace(s):
    return re.match("^[ \t]*$",s)

def isSayNothing(s):
    return isAllSpace(s)

def isYelling(s):
    return hasAlpha(s) and s.upper() == s

def isQuestion(s):
    return not isYelling(s) and s[-1] == '?'

def isProperPhrase(s):
    return hasAlpha(s) or isQuestion(s)

def hey(what):
    
    if isSayNothing(what):
        response = "Fine. Be that way!"
    elif isProperPhrase(what):
        if isQuestion(what):
            response =  "Sure."
        elif isYelling(what):
            response =  "Whoa, chill out!";
        else:
            response =  "Whatever.";
    else:
        response =  "Whatever.";

    return response
