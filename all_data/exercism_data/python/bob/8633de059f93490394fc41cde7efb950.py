""" exercism - python/bob """
import re

class Bob(object):
    """Bob is a lackadasical teenager"""

    def hey(self, msg):
        """used to say something to Bob"""

        if self.silent(msg): 
            return 'Fine. Be that way!'
        if self.shouty(msg):
            return 'Woah, chill out!'
        if self.asking(msg):
            return 'Sure.'
        return 'Whatever.'

    @staticmethod
    def silent(msg):
        return (msg == None) or (msg.strip() == '')
    
    @staticmethod
    def shouty(msg):
        #return re.search('[A-Z]', msg) and (msg == msg.upper())
        return StringHelpers.hasalpha(msg) and (msg == msg.upper())

    @staticmethod
    def asking(msg):
        return msg.endswith('?')

class StringHelpers:
    @staticmethod
    def hasalpha(s):
        return re.search('[A-z]', s)
