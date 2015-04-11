# -*- coding: utf-8 -*-

def hey(input):

    if input.strip().__len__() == 0:
        return 'Fine. Be that way!'

    elif input.isupper():
        return 'Whoa, chill out!'
    
    elif input[-1:] == '?':
        return 'Sure.'
    
    else:
        return 'Whatever.'
