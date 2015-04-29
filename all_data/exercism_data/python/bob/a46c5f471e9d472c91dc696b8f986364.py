# -*- coding: utf-8 -*-


def hey(msg):
    if msg.strip() == '':
        reply = 'Fine. Be that way!'
    elif msg.isupper():
        reply = 'Whoa, chill out!'
    elif msg[-1] == '?':
        reply = 'Sure.'
    else:
        reply = 'Whatever.'
    return reply
