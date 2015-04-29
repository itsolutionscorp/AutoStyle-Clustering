import re

def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    # elif what.strip() == '':
    # elif what.isspace() or what == '':
    elif re.search(r'^\s*$', what):
        return 'Fine. Be that way!'
    elif what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
