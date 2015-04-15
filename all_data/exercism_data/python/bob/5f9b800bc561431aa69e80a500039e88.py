import re

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if (re.match('^\s*$', what)):                   # catch empty strings and silence
        return 'Fine. Be that way!'
    if (what == what.upper()
        and not re.match('^[0-9,.?!\s]+$', what)):  # catch shouting (using words)
        return 'Whoa, chill out!'
    if (what[-1] == '?'):                           # catch questions
        return 'Sure.'
    return 'Whatever.'                              # standard response
