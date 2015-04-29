'''
Created on Aug 16, 2013

@author: lunactic
'''
import string

class Bob(object):
    pass
    
    def hey(self,comment):
        if comment.isupper() :
            return 'Woah, chill out!'
        if '?' in comment :
            return 'Sure.'
        if not comment :
            return "Fine. Be that way."
        return "Whatever."
