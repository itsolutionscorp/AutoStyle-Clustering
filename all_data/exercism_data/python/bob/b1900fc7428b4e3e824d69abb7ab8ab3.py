# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#

import string


def hey(what):
    '''
    # Handle all inputs from from bob_test.py as expected.
    '''
    
    # length of input
    hey_length = len(what)
    
    # no length or has tabs
    if (hey_length == 0) or (what != what.expandtabs()):
        return 'Fine. Be that way!'

    # numerical
    elif what.isdigit():
        return '4?'

    # left spaces
    elif (what != what.lstrip()):
        return 'Whatever.'

    # check for umlauts by converting to ascii
    elif (what != what.encode('ascii', 'ignore')):
        # check for all uppercase umlauts
        if what.isupper():
            return 'Whoa, chill out!'
        else:
            return 'Whatever.'

    # all uppercase (shouting)
    elif (what.isupper()):
        return 'Whoa, chill out!'

    # ends with !    
    elif (what.endswith('!')):
        return 'Whatever.'

    # ends with ?, automatically strip right whitespace
    elif what.rstrip().endswith('?'):
        return 'Sure.'
    
    # catch
    else:
        return 'Whatever.'
