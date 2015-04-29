def isYell(line):
    hasChars = False
    hasLower = False
    for char in line:
        hasChars |= char.isalpha()
        if hasChars and char.islower():
            hasLower = True
            break
    return hasChars and not hasLower;

def isQuestion(line):
    return line and line[-1] == '?'

def isEmpty(line):
    return line == "" or line[-1] == '\t'


def hey(what):
    if isYell(what):
        return "Whoa, chill out!"

    if isQuestion(what):
        return "Sure."

    if isEmpty(what):
        return "Fine. Be that way!"

    return "Whatever."
