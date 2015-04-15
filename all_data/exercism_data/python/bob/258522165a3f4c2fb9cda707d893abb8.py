import re

def is_question(s):
    "Does a string end in a question mark?"
    return bool(re.match(".*\?$", s))

def is_yelling(s):
    "Is a string all uppercase?"
    return s == s.upper() and bool(re.match(".*[A-Z].*", s))

def is_blank(s):
    "Does a string contain no text?"
    return s.isspace() or (s == '')

    
def hey(what):
    if is_yelling(what):
        return "Whoa, chill out!"
    elif is_question(what):
        return "Sure."
    elif is_blank(what):
        return "Fine. Be that way!"
    else:
        return "Whatever."
