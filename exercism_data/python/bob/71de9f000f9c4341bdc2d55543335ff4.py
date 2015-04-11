# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(msg):
    if len(msg.strip()) > 0:
        if not msg.isspace() and msg.isupper():
            response = 'Whoa, chill out!'
        elif msg.endswith('?'):
            response = 'Sure.'
        else:
            response = 'Whatever.'
    else:
        response = 'Fine. Be that way!'

    return response
