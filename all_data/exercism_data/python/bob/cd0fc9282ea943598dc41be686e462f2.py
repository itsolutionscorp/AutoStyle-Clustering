#!/bin/python

def hey(msg):
    if not msg.strip():
        return 'Fine. Be that way!'
    if msg.isupper():
        return 'Whoa, chill out!'
    if msg[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
