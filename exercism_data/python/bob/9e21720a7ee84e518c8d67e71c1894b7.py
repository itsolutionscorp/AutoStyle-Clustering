# -*- coding: utf-8 -*-
from __future__ import unicode_literals

""" 
Exercism Python Practice
Filename: bob.py 
Problem Description: Program designed to simulate Bob the teenager Bob answers 'Sure.' to questions, 'Whoa chill out!' to yelling, 'Fine be that way!' to silence, 'Whatever' to anything else. 
Inputs: String indicating the thing being said to Bob
Outputs: Bob's response as a string

"""
# Method for bob's response
def hey(inputString):
    # Check if input is empty
    if (len(inputString.rstrip()) == 0):
        isEmpty = True
    else:
        isEmpty = False
    # Check if input is in all caps (and contains at least one letter)
    if (inputString.upper() == inputString and any(c.isalpha() for c in inputString)):
        isYelling = True
    else:
        isYelling = False
    # If empty, return appropriate response
    if (isEmpty):
        return "Fine. Be that way!"
    # If ending in question mark, and not yelling, return appropriate response
    elif (inputString[-1] == '?' and not isYelling):
        return 'Sure.'
    # If yelling, return appropriate response
    elif (isYelling):
        return 'Whoa, chill out!'
    # Else return whatever
    else:
        return 'Whatever.'
