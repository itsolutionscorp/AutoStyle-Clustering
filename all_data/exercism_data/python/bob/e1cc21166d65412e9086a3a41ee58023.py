# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(msg):
    if len(msg) > 0 and not msg.isspace():
        if msg.isupper():
            response = 'Whoa, chill out!'
        elif msg.endswith('?'):
            response = 'Sure.'
        else:
            response = 'Whatever.'
    else:
        response = 'Fine. Be that way!'

    return response
