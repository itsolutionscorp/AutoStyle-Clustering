# -*- coding: utf-8 -*-


def hey(msg):
    if msg.strip() == '':
        reply = 'Fine. Be that way!'
    elif msg == msg.upper() and ''.join(i for i in msg if i.isalpha()) != '':
        reply = 'Whoa, chill out!'
    elif msg[-1] == '?':
        reply = 'Sure.'
    else:
        reply = 'Whatever.'
    return reply
