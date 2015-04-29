#
# Skeleton file for the Python "Bob" exercise.
#
import re
def hey(what):
    if what.isupper():
        response = 'Whoa, chill out!'
    elif re.findall('\?\s*$', what):
        response = 'Sure.'
    elif re.findall('^\s*$', what):
        response = 'Fine. Be that way!'
    else:
        response = 'Whatever.'
    return response
