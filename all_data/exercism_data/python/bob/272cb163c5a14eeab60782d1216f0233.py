# -*- coding: utf-8 -*-


def hey(msg):
    '''Solution for the Python bob exercise at exercism'''
    msg = msg.strip()
    if len(msg) == 0:
        return 'Fine. Be that way!'
    elif msg.isupper():
        return 'Woah, chill out!'
    elif msg[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
