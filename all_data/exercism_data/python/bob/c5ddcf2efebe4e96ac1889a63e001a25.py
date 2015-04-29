#!/usr/bin/env python
# -*- coding: utf-8 -*-

def hey(what):
    
    yell = what.isupper()
    question = what.endswith('?')
    silence = not what.strip()
    
    if yell:
        response = 'Whoa, chill out!'
    elif question:
        response = 'Sure.'
    elif silence:
        response = 'Fine. Be that way!'
    else:
        response = 'Whatever.'
    
    return response
