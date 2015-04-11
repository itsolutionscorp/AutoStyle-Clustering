"""
Created on Tue Sep 23 17:37:03 2014

@author: marc
"""

from unicodedata import category

def hey(talk_str):
    if talk_str.isupper():
        return u'Woah, chill out!' # all letters uppercase 
    elif [c for c in talk_str 
        if category(c)[0]=='L' or category(c)[0]=='N'] == []:
        return u'Fine. Be that way!' # no letters and no numbers
    elif talk_str[-1] == '?':  # if not all letters uppercase
        return u'Sure.'
    else :
        return u'Whatever.'

 
