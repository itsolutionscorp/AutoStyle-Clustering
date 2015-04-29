import re

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    has_alpha = False
    has_lower = False
    for c in what:
        if c.isalpha():
            has_alpha = True
            if c.islower():
                has_lower = True
                break

    if has_alpha and not has_lower:
        return 'Whoa, chill out!'

    if re.match('^.*\?\s*$', what):
        return 'Sure.'

    if re.match('^\s*$', what):
        return 'Fine. Be that way!'

    return 'Whatever.'
