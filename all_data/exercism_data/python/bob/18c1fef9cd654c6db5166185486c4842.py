def is_question(what):
    return len(what) > 0 and what[-1] == '?'

def said_nothing(what):
    return what.isspace() or len(what) == 0
    
def is_yelling(what):
    return what.isupper()

def hey(what):
    if said_nothing(what):  return "Fine. Be that way!"
    elif is_yelling(what):  return "Whoa, chill out!"
    elif is_question(what): return "Sure."
    else:                   return "Whatever."
