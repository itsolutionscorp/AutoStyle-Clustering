import re

def hey(what):
    what = what.strip()
    if len(what) == 0:
        return 'Fine. Be that way!'
    elif what == what.upper() and re.search('[A-Za-z]', what) is not None:
        return 'Whoa, chill out!'
    elif what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
