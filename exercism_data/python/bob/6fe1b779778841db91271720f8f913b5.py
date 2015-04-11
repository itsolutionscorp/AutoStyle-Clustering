# -*- coding: utf-8 -*-

def hey(text):

    # remove leading and trailing whitespace
    trimmed = text.strip()
    
    # No text entered.
    if trimmed == "":
        return 'Fine. Be that way!'

    # All uppercase is considered SHOUTING 
    if trimmed.isupper() :
        return 'Whoa, chill out!'
    
    # Questions end with ?    (when trimmed, of course)
    
    if text.endswith('?') :
        return 'Sure.'
        

    return 'Whatever.'
