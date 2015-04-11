# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 16:31:02 2014
Last modified on Fri Sep 25 16:32:24 2014
@author: rebuhr
"""

def hey(msg):
    msg = msg.strip()    
    if not msg:
        return "Fine. Be that way!"
    if msg.isupper():
        return 'Whoa, chill out!'
    elif msg.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
