#!/usr/bin/env python

"""Bob"""

__author__ = u'David Muse'
__date__ = u'2014-09-24'
__version__ = u'0.0.2'

def hey(statement):
    """
    @param statement String
    @returns String
    @brief hey
    """

    if not statement.strip(): # Check for blank input
        return 'Fine. Be that way!'

    elif statement.isupper(): # Check for all upper case
        return 'Whoa, chill out!'

    elif statement.endswith('?'): # Check for trailing question mark
        return 'Sure.'

    else: # Default response
        return 'Whatever.'
