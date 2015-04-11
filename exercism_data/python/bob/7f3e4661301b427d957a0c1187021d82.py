#
# Skeleton file for the Python "Bob" exercise.
#
import re
from string import punctuation

def hey(what):
    what = unicode(what)
    what = what.strip()

    # To check if we are yelling at bob we must check
    # that each letter is uppercased. To do so we filter
    # out digits and punctuation.
    re_filter = '[0-9%s]' % punctuation
    letters = re.sub(re_filter, '', what)
    letters = letters.strip()

    if letters and letters == letters.upper():
        response = 'Whoa, chill out!'
    elif what and what[-1] == '?':
        response = 'Sure.'
    elif what == '':
        response = 'Fine. Be that way!'
    else:
        response = 'Whatever.'

    return response
