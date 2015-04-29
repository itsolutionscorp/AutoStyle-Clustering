import re

def hey(what):
    if re.search(r'\w', what):
        if what.isupper():
            return 'Whoa, chill out!'
        elif what[-1] == "?":
            return 'Sure.'
        else:
            return 'Whatever.'
    else:
        return 'Fine. Be that way!'
