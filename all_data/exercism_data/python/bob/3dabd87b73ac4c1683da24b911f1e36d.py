'''
Created on Aug 16, 2013

@author: lunactic
'''
class Bob(object):
    def isScreaming(self, comment):
        return comment.isupper()
    def isQuestion(self, comment):
        return comment.endswith('?')
    def isSilence(self, comment):
        return not comment
    
    def hey(self, comment):
        if self.isScreaming(comment):
            return 'Woah, chill out!'
        if self.isQuestion(comment):
            return 'Sure.'
        if self.isSilence(comment):
            return "Fine. Be that way."
        return "Whatever."
    
