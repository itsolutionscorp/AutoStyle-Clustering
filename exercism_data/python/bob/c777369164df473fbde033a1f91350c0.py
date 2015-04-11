import re

def hey(what):
    # trim whitespaces
    what = what.strip(' \t\n\r')
    if(is_silence(what)):
        return "Fine. Be that way!"
    elif is_shouting(what):
        return "Whoa, chill out!"
    elif is_asking(what):
        return "Sure."
    else:
        return "Whatever."

def is_shouting(what):
    return what.upper() == what and re.search("[A-Z]",what) !=None

def is_asking(what):
    return what[-1]=="?"

def is_silence(what):
    return re.search("^ *$",what) != None
