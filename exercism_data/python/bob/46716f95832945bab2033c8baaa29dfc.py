# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 16:31:02 2014

@author: rebuhr
"""

class Bob(object):
    def __init__(self, hey):
        hey = raw_input()
        if hey == hey.upper():
            return 'Whoa, chill out!'
        elif '?' in hey:
            return "Sure."
        elif hey == '':
            return "Fine. Be that way!"
        elif hey is None:
            return "Fine. Be that way!"
        elif hey == '    \t':
            return "Fine. Be that way!"
        else:
            return "Whatever."
