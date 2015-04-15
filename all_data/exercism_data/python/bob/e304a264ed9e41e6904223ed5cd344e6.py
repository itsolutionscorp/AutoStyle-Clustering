#a question string ends with a ? and is not all-caps
def isQuestion(what):
    return what.endswith("?") and not what.isupper()

#a yell string is made of capital letters and special characters
def isYell(what):
    return what.isupper()

#a nothing string is all whitespace
def isNothing(what):
    return what.strip()==""

def hey(what):
    if isYell(what):
        return "Whoa, chill out!"
    elif isQuestion(what):
        return "Sure."
    elif isNothing(what):
        return "Fine. Be that way!"
    else:
        return "Whatever."
