# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 16:31:02 2014
Last modified on Fri Sep 25 16:32:24 2014
@author: rebuhr
"""

def hey(msg):
    if msg.isupper():
        return 'Whoa, chill out!'
    elif msg.endswith('?'):
        return "Sure."
    elif msg == '':
        return "Fine. Be that way!"
    elif msg is None:
        return "Fine. Be that way!"
    elif msg == '    \t':
        return "Fine. Be that way!"
    else:
        return "Whatever."
