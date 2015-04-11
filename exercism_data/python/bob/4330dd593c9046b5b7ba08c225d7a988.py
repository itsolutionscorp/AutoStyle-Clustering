# -*- coding: utf-8 -*-

def hey(what):
    
    w = what.strip()
    
    if not w:
        return 'Fine. Be that way!'
    elif w.isupper():
        return 'Whoa, chill out!'
    elif w.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
