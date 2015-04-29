# -*- coding: utf-8 -*-

def hey(s):
    if s.isupper():
        return 'Whoa, chill out!'
    elif s.endswith('?'):
        return 'Sure.'
    elif s.strip()=='':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
