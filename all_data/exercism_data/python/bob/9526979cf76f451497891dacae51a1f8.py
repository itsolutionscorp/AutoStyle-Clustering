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

    return string.isspace()

def isYelling( what ):
    if hasAlpha( what ) and isAllCaps( what ):
        return True
    else:
        return False

def isQuestion( string ):
    string = string.strip()
    return string.endswith( "?" ) 

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
