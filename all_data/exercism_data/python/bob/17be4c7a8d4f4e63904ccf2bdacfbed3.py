class Bob(object):
    def hey(self, query):
        if not query:
            # Blank query
            return 'Fine. Be that way.'

        if self.is_question(query):
            return 'Sure.'

        if self.is_shouting(query):
            return 'Woah, chill out!'

        return 'Whatever.'

    def is_question(self, query):
        return '?' in query

    def is_shouting(self, query):
        return query.upper() == query
