"""Bob is a lackadaisical teenager."""

import re

class Bob(object):
    """In conversation, his responses are very limited"""

    def hey(self, message):
        '''returns appropriate response to provided message'''
        if message.isupper():
            # UPPER CASE SHOUTING
            return "Woah, chill out!"
        elif re.match(r'^\s*$', message):
            # empty message
            return 'Fine. Be that way!'
        elif re.match(r'.*\?\s*$', message):
            # question
            return "Sure."
        else:
            # anything else
            return "Whatever."
