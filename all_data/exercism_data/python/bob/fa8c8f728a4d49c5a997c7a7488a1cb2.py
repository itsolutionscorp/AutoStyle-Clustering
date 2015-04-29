# -*- coding: utf-8 -*-

# 
# Skeleton file for the Python "Bob" exercise.
#

from __future__ import unicode_literals
    
def hey(what):

    WHATEVER = 'Whatever.'
    WHOA = 'Whoa, chill out!'
    SURE = 'Sure.'
    FINE = 'Fine. Be that way!'
    
    
    if (what.isupper()):
        return WHOA
    elif (what.find("?", len(what) - 1) > 0):
        return SURE  
    elif (what.isspace() or what == ''):
        return FINE
    else:
        return WHATEVER

    return
