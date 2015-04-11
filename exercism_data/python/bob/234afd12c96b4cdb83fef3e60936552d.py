"""
Created on Tue Sep 23 17:37:03 2014

@author: marc
"""

from unicodedata import category

def hey(talk_str):
    if talk_str.decode('utf8').isupper(): # all letters uppercase 
        return u'Woah, chill out!' 
    elif talk_str.strip() == '': # only blank chars
        return u'Fine. Be that way!' 
    elif talk_str.strip()[-1] == '?':  # if last no blank char is '?'
        return u'Sure.'
    else :
        return u'Whatever.'

 
