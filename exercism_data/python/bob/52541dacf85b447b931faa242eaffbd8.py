def isYell(line):
    hasLower = any(char for char in line if char.islower())
    hasAlpha = any(char for char in line if char.isalpha())
    return hasAlpha and not hasLower;

def isQuestion(line):
    return line.endswith('?')

def isEmpty(line):
    return not line or line.endswith('\t')


def hey(what):
    if isYell(what):
        return "Whoa, chill out!"

    if isQuestion(what):
        return "Sure."

    if isEmpty(what):
        return "Fine. Be that way!"

    return "Whatever."
