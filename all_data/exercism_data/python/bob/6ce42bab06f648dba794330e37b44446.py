#!/usr/bin/env python

def hey(message):
    if message.isupper():
        return 'Whoa, chill out!'
    elif message.strip() == '':
        return 'Fine. Be that way!'
    elif message[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
