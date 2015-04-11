#!/usr/bin/env python

QUESTION_RESPONSE = 'Sure.'
YELL_RESPONSE = 'Whoa, chill out!'
EMPTY_RESPONSE = 'Fine. Be that way!'
OTHER_RESPONSE = 'Whatever.'

def hey(input):
    if not input.strip():
        return EMPTY_RESPONSE
    elif input.isupper():
        return YELL_RESPONSE
    elif input.endswith('?'):
        return QUESTION_RESPONSE
    else:
        return OTHER_RESPONSE
