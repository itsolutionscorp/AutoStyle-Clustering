#
# Skeleton file for the Python "Bob" exercise.
#
from __future__ import unicode_literals


def hey(what):
    what = what.strip()
    if not what:
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
