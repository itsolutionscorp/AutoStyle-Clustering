#!/usr/bin/python
# -*- coding: utf-8 -*-

'''
Bob Module for Exercism python test
'''

from re import match

def hey(phrase):
    '''
    The main 'Hey' fun
    '''
    # If being addressed with no content
    if match(r"^\s*$", phrase):
        return "Fine. Be that way!"
    # If being asked a question
    if match(r"^.*\?$", phrase) and not phrase.isupper():
        return "Sure."
    # If being yelled at
    if phrase.isupper():
        return "Whoa, chill out!"
    # Everything else
    return "Whatever."
