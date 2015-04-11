# -*- coding: utf-8 -*-

def hey(input):

    if not input.strip(): #empty string?
        return 'Fine. Be that way!'
    elif input.isupper(): 
        if input.endswith('?') or input.endswith('!'):
            return 'Whoa, chill out!'
        elif 'HATE' in input:
            return 'Whoa, chill out!'
        else:
            return 'Whatever.'
    elif input.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
