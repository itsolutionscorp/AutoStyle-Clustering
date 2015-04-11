'''
Created on Aug 16, 2013

@author: lunactic
'''
class Bob(object):
    def hey(self, comment):
        if comment.isupper():
            return 'Woah, chill out!'
        if comment.endswith('?'):
            return 'Sure.'
        if not comment:
            return "Fine. Be that way."
        return "Whatever."
