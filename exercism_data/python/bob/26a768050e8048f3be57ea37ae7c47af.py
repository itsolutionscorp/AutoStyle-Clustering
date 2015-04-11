#
# Skeleton file for the Python "Bob" exercise.
#

import re

def is_yelling(what):
    words = filter(lambda x: x.isalpha() or x == ' ', what).split()
    yells = filter(unicode.isupper, words)
    return len(yells) >= len(words) and words != []

def hey(what):
    if re.match('^\s*$', what.strip()):
        return 'Fine. Be that way!'
    elif is_yelling(what):
        return 'Whoa, chill out!'
    elif re.match('^.+\?$', what.strip()):
        return 'Sure.'
    else:
        return 'Whatever.'
