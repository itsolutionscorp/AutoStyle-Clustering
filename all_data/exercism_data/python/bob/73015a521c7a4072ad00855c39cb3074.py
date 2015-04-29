# -*- coding: utf-8 -*-

from __future__ import unicode_literals
import string

# Create string of uppercase umlaut for matching when shouting
umlautupper = unichr(196)+unichr(203)+unichr(207)+unichr(214)+unichr(220)

def hey(convo):
    
    convo = convo.strip()
    
    # if you stare at bob blankly without saying a word...
    if not convo:
        return 'Fine. Be that way!'
    
    # if you are shouting at bob
    noupper = True
    for i,letter in enumerate(convo):
        if letter in string.ascii_uppercase or letter in umlautupper:
            noupper = False
        elif not letter in string.punctuation and not letter in ' \t\n\r' and \
                not letter in string.digits:
                
                break
                
        if i == len(convo) - 1 and not noupper:
            return 'Whoa, chill out!'
    
    # If conversation is a question
    if convo[-1]=='?':
        return 'Sure.'

        
    # Bob responds to anything else
    return 'Whatever.'
