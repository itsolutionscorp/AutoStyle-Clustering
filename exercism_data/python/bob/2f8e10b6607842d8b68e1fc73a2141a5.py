#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
    def hasAlpha(what):
        return any(c.isalpha() for c in what)
    def allUpper(what):
        return what.upper() == what

    what = what.strip()
    if len(what) == 0:
        return 'Fine. Be that way!'
    elif hasAlpha(what) and allUpper(what):
        return 'Whoa, chill out!'
    elif what[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
