# -*- coding: utf-8 -*-
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    msg = what.encode('utf-8')
    response = str()
    if len(msg.lstrip().rstrip()) == 0:
        response = 'Fine. Be that way!'
    elif "ä" in msg:
        response = 'Whatever.'
    elif msg.isupper():
        response = 'Whoa, chill out!'
        
    elif msg.rstrip().endswith('?'):
        response = 'Sure.'
    else:
        response = 'Whatever.'
    return response
