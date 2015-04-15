#
# Skeleton file for the Python "Bob" exercise.
#
import re


def hey(what):
    what = re.sub('[ \t,.!%d]', '', what)
    if not what:
        return 'Fine. Be that way!'
    elif what == what.upper() and what != what.lower():
        return 'Whoa, chill out!'
    elif what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
