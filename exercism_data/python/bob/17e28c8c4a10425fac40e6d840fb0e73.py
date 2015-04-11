# -*- coding: utf-8 -*-
# "Bob's lackadaisical responses.
#
def hey(what):
    msg = what.strip()
    if being_slient(msg):
        response = 'Fine. Be that way!'
    elif being_shouted(msg):
        response = 'Whoa, chill out!'
    elif being_questioned(msg):
        response = 'Sure.'
    else:
        response = 'Whatever.'
    return response

def being_slient(msg):
    return not msg
def being_shouted(msg):
    return msg.isupper()
def being_questioned(msg):
    return msg.endswith('?')
