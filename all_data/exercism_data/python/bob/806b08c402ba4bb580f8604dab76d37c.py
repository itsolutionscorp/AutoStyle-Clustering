import re

def allcaps(text):
    least_one_cap = False
    for i in text:
        i = unicode(i)
        if unicode.islower(i):
            return False
        elif not re.match(ur'[^a-z]', i, re.M|re.UNICODE):
            return False
        if unicode.isalpha(i):
            least_one_cap = True 
    return least_one_cap

def hey(what):
    what = what.strip()
    if re.match(r'^[\s]*$', what, re.M):
        return "Fine. Be that way!"
    elif allcaps(what): # elif re.match(ur'^[^a-z]*[A-Z]+[^a-z]*$', text, re.M|re.UNICODE):
        return "Whoa, chill out!"
    elif what[-1:] == "?":
        return "Sure."
    else:
        return "Whatever."
