# -*- coding: utf-8 -*-
"""
Created on Tue Sep 23 16:56:52 2014

@author: johann
"""


    
def hey(message):
    if message.strip() == '':
        return 'Fine. Be that way!'
    if message.isupper():
        return 'Woah, chill out!'
    if message.endswith("?"):
        return "Sure."
    return 'Whatever.'
            
