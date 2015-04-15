#!/usr/bin/env python2
"""This module codes 'bob,' the lackadaisical teenager"""

def hey(prompt):
    """
    Return bob's response to prompt.

    Keyword arguments:
    prompt -- string
    """
    if prompt == '' or prompt.isspace():
        return 'Fine. Be that way!'
    elif prompt.isupper():
        return 'Woah, chill out!'
    elif prompt[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
