import re

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if what.isupper():
        return 'Whoa, chill out!'

    if re.match('^.*\?\s*$', what):
        return 'Sure.'

    if re.match('^\s*$', what):
        return 'Fine. Be that way!'

    return 'Whatever.'
