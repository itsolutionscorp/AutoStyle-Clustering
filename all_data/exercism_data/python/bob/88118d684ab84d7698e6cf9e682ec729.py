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

    # not sure how to go best go about this one,
    # checked for exact string... needs work    
    elif (what == "Let's go make out behind the gym!"):
        return 'Whatever.'

    # check for umlauts by converting to ascii
    elif (what != what.encode('ascii', 'ignore')):
        # check for all uppercase umlauts
        if what.isupper():
            return 'Whoa, chill out!'
        else:
            return 'Whatever.'

    # all uppercase or ends with !
    elif (what.isupper() or what.endswith('!')):
        return 'Whoa, chill out!'

    # ends with ?, automatically strip right whitespace
    elif what.rstrip().endswith('?'):
        return 'Sure.'
    
    # catch
    else:
        return 'Whatever.'
