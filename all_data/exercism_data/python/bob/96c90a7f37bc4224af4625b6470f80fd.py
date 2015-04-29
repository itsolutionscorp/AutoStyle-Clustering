#
# Skeleton file for the Python "Bob" exercise.
#

import re

def hey(what):
    if what.isupper() and re.search(r'[a-zA-Z]', what):
        # All letters are uppercase.
        return 'Whoa, chill out!'
    elif re.search(r'\?$', what):
        # There is question amrk at the end of the string.
        return 'Sure.'
    elif not re.search(r'\w', what):
        # There are no words.
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
