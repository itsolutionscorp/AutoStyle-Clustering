#
# Skeleton file for the Python "Bob" exercise.
#
import re
uppercase = r'[A-Z]'

def hey(what):
    what = what.strip()
    if len(what) == 0:
        return 'Fine. Be that way!'
    elif what == what.upper() and len(filter(lambda c: re.match(uppercase, c),what)) > 0:
        return 'Whoa, chill out!'
    elif what[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
