import re

class Bob:
    """
    A simple class describing Bob who can reply to simple
    statements via the hey() method.
    """
    def __init__(self):
        # A shout is at least one capital letter and any number of non-
        # alphabetic characters
        self.shout = re.compile(r'^([^a-z]*[A-Z][^a-z]*)+$')

    def hey(self, query):
        if len(query) == 0:
            return 'Fine. Be that way.'
        elif query[-1] == '?':
            return 'Sure.'
        elif re.match(self.shout, query):
            return 'Woah, chill out!'
        elif len(query) > 0:
            return 'Whatever.'
