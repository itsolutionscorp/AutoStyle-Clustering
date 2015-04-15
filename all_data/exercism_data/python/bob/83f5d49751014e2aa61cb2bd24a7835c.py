# -*- coding: utf-8 -*-
"""
Created on Sat Feb  8 17:25:01 2014

@author: steffendeubler
"""

class Bob:
    def hey(self, blabla):
        
        single_words = blabla.split()
        uppercase = [w for w in single_words if w.isupper()]

        if len(single_words) == 0:
           return 'Fine. Be that way!'
           
        elif single_words[-1].endswith('?') and not len(uppercase) == len(single_words):
            return 'Sure.'
           
        elif len(uppercase) > 0 and not (len(uppercase) == 2 and uppercase[0] == 'OK'):
            return 'Woah, chill out!'
        
        return "Whatever."
