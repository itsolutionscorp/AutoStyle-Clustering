import re

def hey(what):
    what = what.strip()
    if what.isupper():
        return 'Whoa, chill out!'
    elif len(what) == 0:
        return 'Fine. Be that way!'
    elif re.search('\?$', what):
        return 'Sure.'
    return 'Whatever.'
