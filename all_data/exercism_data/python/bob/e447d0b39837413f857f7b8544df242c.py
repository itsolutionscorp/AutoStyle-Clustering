import re

def hasAlpha(string):
    return re.search('[a-zA-Z]', string)

def hey(what):
    what = what.strip()
    if len(what) <= 0:
        return 'Fine. Be that way!'
    if hasAlpha(what) and what.upper() == what:
        return 'Whoa, chill out!'
    if what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
