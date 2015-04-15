class Bob(object):
    def hey(self, query):
        if self.is_blank_query(query):
            return 'Fine. Be that way.'

        elif self.is_question(query):
            return 'Sure.'

        elif self.is_shouting(query):
            return 'Woah, chill out!'

        else:
            return 'Whatever.'

    def is_blank_query(self, query):
        return not query

    def is_question(self, query):
        return query.endswith('?')

    def is_shouting(self, query):
        return query.isupper()
