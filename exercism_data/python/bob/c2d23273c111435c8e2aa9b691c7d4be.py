import re

class Bob:
    """
    A simple class describing Bob who can reply to simple
    statements via the hey() method.
    """
    def __init__(self):
        self.SHOUT = 0
        self.QUESTION = 1
        self.SILENCE = 2
        self.STATEMENT = 3

    def interpret_query(self, query):
        if len(query) == 0:
            return self.SILENCE
        if query.endswith('?'):
            return self.QUESTION
        if query == query.upper():
            return self.SHOUT
        return self.STATEMENT

    def hey(self, query):
        query_type = self.interpret_query(query)
        if query_type == self.SILENCE:
            return 'Fine. Be that way.'
        elif query_type == self.QUESTION:
            return 'Sure.'
        elif query_type == self.SHOUT:
            return 'Woah, chill out!'
        elif query_type == self.STATEMENT:
            return 'Whatever.'
