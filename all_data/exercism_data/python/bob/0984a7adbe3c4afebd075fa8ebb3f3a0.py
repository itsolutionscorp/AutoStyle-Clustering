#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
    # Check for any upper-case characters and no lower-case
    if any(c for c in what if c.isupper()) and not any(c for c in what if c.islower()):
        return 'Whoa, chill out!'
    # Check for ending in a question mark disregarding trailing whitespace
    if re.search('\?\s*$', what):
        return 'Sure.'
    # Check for only whitespace
    if re.search('^\s*$', what):
        return 'Fine. Be that way!'
    # Default case
    return 'Whatever.'
