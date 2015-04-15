#
# Skeleton file for the Python "Bob" exercise.
#

import re

def hey(what):
    what = what.strip()
    if not what:
        return "Fine. Be that way!"
    if any(map(lambda ch: ch.isalpha(), what)) and what.upper() == what:
        return 'Whoa, chill out!'
    if what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
