#
# Skeleton file for the Python "Bob" exercise.
#
import string

def hey(what):
    if what.strip() == '':
        return 'Fine. Be that way!'
    elif what == what.upper():
        return 'Whoa, chill out!'
    elif what.strip()[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
