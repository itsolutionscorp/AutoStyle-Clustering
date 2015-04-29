#
# Skeleton file for the Python "Bob" exercise.
#

import fnmatch

def hey(what):
    if (what.isspace() or not what):
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    if fnmatch.fnmatch(what,'*[?]'):
        return 'Sure.'

    return 'Whatever.'
