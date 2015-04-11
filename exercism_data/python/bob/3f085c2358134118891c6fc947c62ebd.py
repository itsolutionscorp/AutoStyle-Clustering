#!/usr/bin/env python

def hey(conv):
    responses = {}
    responses['question'] = 'Sure.'
    responses['yell'] = 'Whoa, chill out!'
    responses['empty'] = 'Fine. Be that way!'
    responses['default'] = 'Whatever.'
    if len(conv) == 0 or conv.isspace():
        return responses['empty']
    elif conv == conv.upper() and conv.lower() != conv.upper():
        return responses['yell']
    elif conv.endswith('?'):
        return responses['question']
    else:
        return responses['default']
