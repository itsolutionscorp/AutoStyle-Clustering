# -*- coding: utf-8 -*-

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    # suspiciously looking on him and don't ask anything
    if what.strip() == '':
        return 'Fine. Be that way!'
    
    # shouthing
    if what.isupper():
        return 'Whoa, chill out!'

    # asking
    elif what.strip().endswith('?'):
        return 'Sure.'

    return 'Whatever.'
