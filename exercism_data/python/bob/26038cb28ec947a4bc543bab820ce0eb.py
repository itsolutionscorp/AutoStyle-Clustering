# -*- coding: utf-8 -*-


def contains_letter(msg):
    return any(c.isalpha() for c in msg)


def hey(msg):
    if len(msg.strip()) > 0:
        if contains_letter(msg) and msg.upper() == msg:
            response = 'Whoa, chill out!'
        elif msg[-1] == '?':
            response = 'Sure.'
        else:
            response = 'Whatever.'
    else:
        response = 'Fine. Be that way!'

    return response
