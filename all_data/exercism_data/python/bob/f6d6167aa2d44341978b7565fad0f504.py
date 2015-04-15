# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if not what.isupper() and what.strip().endswith('?'):
        return 'Sure.'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif len(what.strip()) == 0:
        return "Fine. Be that way!"
    else:
        return 'Whatever.'
