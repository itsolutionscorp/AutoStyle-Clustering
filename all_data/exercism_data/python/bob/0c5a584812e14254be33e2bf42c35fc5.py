#!/usr/bin/env python

def hey(in_string):
    in_string = in_string.strip()
    
    if not in_string:
        return 'Fine. Be that way!'
    elif in_string.isupper():
        return 'Whoa, chill out!'
    elif in_string[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
