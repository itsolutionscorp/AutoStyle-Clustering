#!/usr/bin/env python

def hey(msg):
    if msg.isupper():
        return 'Whoa, chill out!'
    if msg.endswith('?'):
        return 'Sure.'
    if msg.expandtabs().replace(' ', '') == '':
        return "Fine. Be that way!"
    return 'Whatever.'
