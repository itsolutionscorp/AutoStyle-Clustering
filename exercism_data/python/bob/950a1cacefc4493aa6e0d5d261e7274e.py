#!/usr/bin/env python

def hey(message):
    if message.isupper():
        return 'Whoa, chill out!'
    if message.isspace() or message == '':
        return 'Fine. Be that way!'
    if message != '' and message[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
