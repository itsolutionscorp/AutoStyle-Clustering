#
# Skeleton file for the Python "Bob" exercise.
#
# -*- coding: utf-8 -*-
def hey(what):
    what = what.strip()
    # if the string is empty
    if not what:
        return 'Fine. Be that way!'
    # if we're yelling (upper case letters everywhere)
    elif  what.isupper():
        return 'Whoa, chill out!'
    # if its a question (and not yelling)
    elif what.endswith('?'):
        return 'Sure.'
    # everything else.
    else:
        return 'Whatever.'
