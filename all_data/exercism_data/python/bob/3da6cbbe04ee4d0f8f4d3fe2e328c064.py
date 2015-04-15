#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):

    what = what.strip()

    if len(what) == 0:
        return 'Fine. Be that way!'

    if re.search('[A-Z]+', what) and what.upper() == what:
        return 'Whoa, chill out!'

    if what[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
