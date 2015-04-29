#!/usr/bin/python
"""This code takes a string and returns a phrase based on the string."""
import re

def hey(string=''):
    """Return a phrase based on the given string.

    Arguments:
    string: a given input string
    """
    if (string.upper() == string) and any(x.isalpha() for x in string):
        return 'Woah, chill out!'
    elif re.search(r'\?$', string):
        return 'Sure.'
    elif re.search('[0-9]', string) and any(x.isalpha() for x in string) or \
         re.search(r'^\s+|\t+|\n+$', string) and \
         not any(x.isalpha() for x in string) or \
         string == '':
        return 'Fine. Be that way!'
    return 'Whatever.'
