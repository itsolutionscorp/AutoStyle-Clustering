#
# Skeleton file for the Python "Bob" exercise.
#

import string

def hey(what):

    what = what.strip()

    if what == '':
        return 'Fine. Be that way!'

    if any(char in string.ascii_uppercase for char in what) and what.upper() == what:
        return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
