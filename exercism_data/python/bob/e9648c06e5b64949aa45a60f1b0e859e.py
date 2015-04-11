class Bob:
    """
    A simple class describing Bob who can reply to simple
    statements via the hey() method.
    """
    SHOUT = 0
    QUESTION = 1
    SILENCE = 2
    STATEMENT = 3

    def __interpret_query(self, query):
        if len(query) == 0:
            return self.SILENCE
        if query.endswith('?'):
            return self.QUESTION
        if query == query.upper():
            return self.SHOUT
        return self.STATEMENT

    def hey(self, query):
        query_type = self.__interpret_query(query)
        if query_type == self.SILENCE:
            return 'Fine. Be that way.'
        if query_type == self.QUESTION:
            return 'Sure.'
        if query_type == self.SHOUT:
            return 'Woah, chill out!'
        if query_type == self.STATEMENT:
            return 'Whatever.'
