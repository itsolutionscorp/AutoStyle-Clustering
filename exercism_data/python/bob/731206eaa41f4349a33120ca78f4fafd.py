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
            return SILENCE
        if query.endswith('?'):
            return QUESTION
        if query == query.upper():
            return SHOUT
        return STATEMENT

    def hey(self, query):
        query_type = self.__interpret_query(query)
        if query_type == SILENCE:
            return 'Fine. Be that way.'
        elif query_type == QUESTION:
            return 'Sure.'
        elif query_type == SHOUT:
            return 'Woah, chill out!'
        elif query_type == STATEMENT:
            return 'Whatever.'
