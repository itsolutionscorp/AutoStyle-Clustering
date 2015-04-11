# -*- coding: utf-8 -*-

def hey(text):

    trimmed = text.strip()
    
    # No text entered.
    if trimmed == "":
        return 'Fine. Be that way!'

    # All usercase is considered SHOUTING (ie any Uppercase and no lowercase)
    # Non-alphabetic characters do not affect the outcome.

    if trimmed.isupper() and not trimmed.islower():
        return 'Whoa, chill out!'
    
    # Questions end with ?    
    
    if text.endswith('?') :
        return 'Sure.'
        

    return 'Whatever.'
