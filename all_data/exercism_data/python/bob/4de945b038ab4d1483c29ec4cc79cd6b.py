#!/usr/bin/python
"""This code takes a string and returns a phrase based on the string."""
import re

def hey(string=''):
    """Return a phrase based on the given string.

    Arguments:
    string: a given input string
    """
    if string.isupper():
        return 'Woah, chill out!'
    elif string.endswith('?'):
        return 'Sure.'
    elif string.strip() == '':
        return 'Fine. Be that way!'
    return 'Whatever.'
