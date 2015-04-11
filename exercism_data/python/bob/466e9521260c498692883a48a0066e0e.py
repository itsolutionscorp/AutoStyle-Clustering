import re

def hey(what):
    if what == what.upper() and what != what.lower():
        return 'Whoa, chill out!'
    elif re.search(r'^\s*$', what):
        return 'Fine. Be that way!'
    elif re.search(r'\?$', what):
        return 'Sure.'
    else:
        return 'Whatever.'
