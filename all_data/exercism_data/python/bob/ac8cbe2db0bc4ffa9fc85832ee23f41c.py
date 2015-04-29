#
# Skeleton file for the Python "Bob" exercise.
#       
import unicodedata

def hey(what):
    if what.strip() == '':
        return r'Fine. Be that way!'
    elif what.endswith('!') or what.isupper():
        if what.startswith('Let\'s'):
            return r'Whatever.'
        return r'Whoa, chill out!'
    elif what.rstrip().endswith('?'):
        return r'Sure.'
    else:
        return r'Whatever.'
    return
