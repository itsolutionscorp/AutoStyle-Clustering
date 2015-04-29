# -*- coding: utf-8 -*-
# "Bob's lackadaisical responses.
#
def hey(what):
    msg = what.encode('utf-8')
    msg = msg.strip()
    response = 'Whatever.'
    #No answer
    if being_slient(msg):
        response = 'Fine. Be that way!'
    #being calm and with umlauts
    elif being_calm_and_with_umlants(msg):
        response = 'Whatever.'
    #Being shout
    elif being_shouted(msg):
        response = 'Whoa, chill out!'
    #Being question    
    elif being_questioned(msg):
        response = 'Sure.'
    return response
#not necessary but reserving a method for strings with umlants
def being_calm_and_with_umlants(msg):
    return "ä" in msg
def being_slient(msg):
    return len(msg) == 0
def being_shouted(msg):
    return msg.isupper()
def being_questioned(msg):
    return msg.endswith('?')
