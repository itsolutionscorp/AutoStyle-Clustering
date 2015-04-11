#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
    what = what.strip()
    l = len(what)
    if l == 0:
        return 'Fine. Be that way!'
    elif (not re.search(r'[a-zä-ü]+', what)) and re.search(r'[A-ZÄ-Ü]+', what):
        return 'Whoa, chill out!'
    elif what[l-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
