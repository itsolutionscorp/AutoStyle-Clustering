""" exercism - python/bob """
import re

class Bob(object):
    """Bob is a lackadasical teenager"""
    @staticmethod
    def hey(msg):
        """used to say something to Bob"""
        if (msg == None) or re.search('^[\\s]*$', msg):
            return 'Fine. Be that way!'
        if re.search('[A-Z]', msg) and (msg == msg.upper()):
            return 'Woah, chill out!'
        if re.search('^.*\\?$', msg):
            return 'Sure.'
        return 'Whatever.'
