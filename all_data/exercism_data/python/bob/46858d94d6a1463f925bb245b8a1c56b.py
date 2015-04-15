def hey(what):

    if saidNothing( what ):
        return "Fine. Be that way!"
    elif isYelling( what ):
        return "Whoa, chill out!"
    elif isQuestion( what ):
        return "Sure."
    else:
        return "Whatever."

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
