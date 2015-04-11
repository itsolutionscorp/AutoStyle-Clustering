import re

def hey(str):
    if is_blank(str):
        return "Fine. Be that way!"
    elif is_all_caps(str):
        return "Woah, chill out!"
    elif is_question(str):
        return "Sure."
    else:
        return "Whatever."

def is_blank(str):
    return ( len(str) == 0 ) or ( set(str) == set([' ']) )

def is_all_caps(str):
    unicoded = str.encode('utf8')
    return ( unicoded.decode('utf8').isupper() )

def is_question(str):
    return ( re.search(r"\?\Z", str) != None )
