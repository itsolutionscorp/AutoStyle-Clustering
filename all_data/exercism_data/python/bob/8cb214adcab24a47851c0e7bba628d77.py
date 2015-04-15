'''
Created on Jun 23, 2014

@author: james
'''
import re

class Bob:
    
    def hey(self, aString):
        if  aString.isupper():
            return 'Woah, chill out!'
        if  aString.isspace() or aString == '':
            return 'Fine. Be that way!'
        if aString.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
        
