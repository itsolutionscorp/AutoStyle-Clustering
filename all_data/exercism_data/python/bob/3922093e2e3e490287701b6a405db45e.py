import re

WHATEVER = "Whatever."
CHILLOUT = "Whoa, chill out!"
SURE = "Sure."
FINE = "Fine. Be that way!"

def hey(what):

    what = what.strip()

    if what == '':
        return FINE

    if what.isupper(): 
        return CHILLOUT

    if what.endswith('?'):
        return SURE

    return WHATEVER
