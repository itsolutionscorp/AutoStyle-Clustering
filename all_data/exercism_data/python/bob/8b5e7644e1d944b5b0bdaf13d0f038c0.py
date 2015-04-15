#!/usr/bin/env python

def hey(message):

    if (message.strip() == ''):
        return 'Fine. Be that way!'

    if (message.isupper()):
        return 'Whoa, chill out!'

    if (message.endswith('?')):
        return 'Sure.'

    return 'Whatever.'
