NOTHING_RESPONSE = "Fine. Be that way!"
YELLING_RESPONSE = "Whoa, chill out!"
QUESTION_RESPONSE = "Sure."
OTHER_RESPONSE = "Whatever."

def hey(what):
    what = what.strip()

    if saidNothing( what ):
        return NOTHING_RESPONSE
    elif isYelling( what ):
        return YELLING_RESPONSE
    elif isQuestion( what ):
        return QUESTION_RESPONSE
    else:
        return OTHER_RESPONSE

def saidNothing( string ):
    return len( string ) == 0

def isYelling( what ):
    return what.isupper() 

def isQuestion( string ):
    return string.endswith( "?" )
