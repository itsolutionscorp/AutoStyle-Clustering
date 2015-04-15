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
        if len(query) == 0: # If there was no statement Bob is disappointed
            return 'Fine. Be that way.'
        elif query[-1] == '?':
            # However, if the final character is a '?', Bob will answer
            return 'Sure.'
        elif re.match(self.shout, query):
            # On the other hand, if the input is ALL CAPS...
            return 'Woah, chill out!'
        elif len(query):
            # Otherwise, we can assume it is a statement
            return 'Whatever.'
