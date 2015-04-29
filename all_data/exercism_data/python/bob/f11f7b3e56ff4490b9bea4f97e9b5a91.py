NOTHING_RESPONSE = "Fine. Be that way!"
YELLING_RESPONSE = "Whoa, chill out!"
QUESTION_RESPONSE = "Sure."
OTHER_RESPONSE = "Whatever."

def hey(what):

    if saidNothing( what ):
        return NOTHING_RESPONSE
    elif isYelling( what ):
        return YELLING_RESPONSE
    elif isQuestion( what ):
        return QUESTION_RESPONSE
    else:
        return OTHER_RESPONSE

def saidNothing( string ):
    if string is None:
        return True

    if len( string ) == 0:
        return True

    for char in string:
        if not char.isspace():
            return False

    return True

def isYelling( what ):
    if hasAlpha( what ) and isAllCaps( what ):
        return True
    else:
        return False

def isQuestion( string ):
    string = string.strip()
    return endsWith( string, "?" )

def hasAlpha( string ):
    for char in string:
        if char.isalpha():
            return True

    return False

def isAllCaps( string ):
    for char in string:
        if char.isalpha() and not char.isupper():
            return False

    return True

def endsWith( string, lastChars ):
    stringLen = len( string )
    lastCharsLen = len( lastChars )

    if stringLen == 0:
        return False

    if lastCharsLen == 0:
        return False

    if stringLen < lastCharsLen:
        return False

    if string[ -lastCharsLen: ] == lastChars:
        return True

    return False
