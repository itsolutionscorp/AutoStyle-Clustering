class Bob:
    """models an annoying teenager"""

    def hey(self, request):
        """responds to a request with an appropriate, though sarcastic response"""
        query = Query(request)

        if query.is_shout():
            return 'Woah, chill out!'

        if query.is_question():
            return 'Sure.'

        if query.is_silence():
            return 'Fine. Be that way!'

        return 'Whatever.'

class Query:
    """provides utilities for classifying a query"""

    def __init__(self, query):
        self._query = query.strip()

    def is_shout(self):
        """returns true if the query is a shout"""
        upper = self._query.upper()
        lower = self._query.lower()

        return self._query == upper and self._query != lower

    def is_question(self):
        """returns true if the query is a question"""
        try:
            return self._query[-1] == '?'
        except IndexError:
            return False

    def is_silence(self):
        """returns true if the query is silence"""
        return self._query == ''
