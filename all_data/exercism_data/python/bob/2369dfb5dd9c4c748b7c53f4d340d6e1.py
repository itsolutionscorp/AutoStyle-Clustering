# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 08:54:41 2014

@author: Dan
"""

def hey(message):
    if message is None or message.strip() == '':
        return 'Fine. Be that way!'
    if message.isupper():
        return 'Whoa, chill out!'
    if message.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
