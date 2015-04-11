import re

RE_ALPHA = '[^\W\d_]' # python thinks _ is alphanumeric

def hey(what):
    if not what.strip():
        return 'Fine. Be that way!'
    elif what == what.upper() and re.search(RE_ALPHA, what):
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
