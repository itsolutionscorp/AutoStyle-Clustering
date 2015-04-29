#
# Skeleton file for the Python "Bob" exercise.
#

import re

def hey(what):
    
    if not what.strip():
        return 'Fine. Be that way!'
    elif what.upper() == what and re.search(r'[A-Z]', what):
        return 'Whoa, chill out!'
    elif what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
