#
# Skeleton file for the Python "Bob" exercise.
#
import re

letters = re.compile('[A-z]')

def hey(what):
    """Respond like a surly teenager."""

    what = what.strip()

    if what == '':
        return 'Fine. Be that way!'

    if letters.search(what) and what.upper() == what:
        return 'Whoa, chill out!'

    if what[-1:] == '?':
        return 'Sure.'

    return 'Whatever.'
