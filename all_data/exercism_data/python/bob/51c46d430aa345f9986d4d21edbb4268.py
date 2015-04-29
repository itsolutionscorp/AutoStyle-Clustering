# -*- coding: utf-8 -*-

def hey(msg):
    if not msg or msg.isspace():
        return "Fine. Be that way!"
    elif msg.isupper():
        return 'Woah, chill out!'
    elif msg.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
