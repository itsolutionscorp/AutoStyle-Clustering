# -*- coding: utf-8 -*-

def hey(s):
    if len(s) == 0 or s.isspace():
        return 'Fine. Be that way!'
    elif s.isupper():
        return 'Whoa, chill out!'
    elif s.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
