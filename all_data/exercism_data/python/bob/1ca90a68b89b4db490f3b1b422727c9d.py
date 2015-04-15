#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    a = 'Sure.'
    b = 'Whatever.'
    c = 'Whoa, chill out!'
    d = 'Fine. Be that way!'

    if isEmpty(what):
        return d

    if hasExclamationPoint(what):
        if shouting(what):
            return c
        elif containsLotsOfPunctuation(what):
            return a
        else:
            return b

    if hasQuestionMark(what):
        if shouting(what):
            return c
        elif hasNumbers(what):
            return a
        elif (what.find('.') != -1):
            return b
        elif isWhiteSpace(what):
            return a
        else:
            return a

    if hasNumbers(what):
        if shouting(what):
            return c
        elif not(hasQuestionMark(what)):
            return b

    if shouting(what):
        if not(containsLotsOfPunctuation(what)):
            return c

    if isWhiteSpace(what):
        if what.find('.') != -1:
            return b
        if not(containsLotsOfPunctuation(what)):
            return d

    if (any.isupper() for char in what):
        if not(shouting(what)):
            return b

    if hasUmlaut(what):
        if shouting(what):
            return c
        else:
            return b

    if containsStrangeChars(what):
        return b

    if not(isEmpty(what)):
        return b


def hasExclamationPoint(inputString):
    return (inputString.find('!') != -1)

def hasNumbers(inputString):
    return any(char.isdigit() for char in inputString)
        
def hasQuestionMark(inputString):
    return (inputString.find('?') != -1)

def hasCharacters(inputString):
    return not(inputString.isspace())

def hasUmlaut(inputString):
    return (inputString.find('Ü') != -1)

def shouting(inputString):
    return (inputString.isupper())

def isEmpty(inputString):
    if not inputString:
        return True
    else:
        return False

def containsStrangeChars(inputString):
    if (inputString.find('%') != -1):
        return True
    else:
        return False

def containsLotsOfPunctuation(inputString):
    if(inputString.find('.') != -1 and inputString.find('!') != -1 and inputString.find('?') != -1):
        return True
    else:
        return False

def isWhiteSpace(inputString):
    if ('   ' in inputString):
        return True
    else:
        return False
