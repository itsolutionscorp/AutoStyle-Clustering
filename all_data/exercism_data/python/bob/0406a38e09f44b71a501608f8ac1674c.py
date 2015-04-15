__author__ = 'BinaryFu'

import re

class Bob(object):
    def hey(self, sentence):
        whitespace_only = re.compile(r'^\s+$')
        
        if not sentence or re.match(whitespace_only, sentence):
            return "Fine. Be that way!"
        elif sentence.isupper():
            return "Woah, chill out!"
        elif sentence[-1:] == "?":
            return "Sure."
        else:
            return "Whatever."
