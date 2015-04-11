import re

def isQuestion(what):
    return re.match(r'.*\?\s*$', what) != None

def isSilence(what):
    return re.match(r'\s*$', what) != None

def isShout(what):
    return re.match(r'.*[a-z\xe4-\xfc]+.*$', what, re.U) == None and re.match(r'.*[A-Z]{2,}.*', what) != None


def hey(what):
    if isSilence(what):
        return "Fine. Be that way!"

    elif isShout(what):
        return "Whoa, chill out!"

    elif isQuestion(what):
        return "Sure."

    else:
        return "Whatever."
