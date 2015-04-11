class Bob(object):
    responses = {
            'A question?': 'Sure.',
            'SHOUTING': 'Woah, chill out!',
            'A statement.': 'Whatever.',
            }

    def hey(self, query):
        return self.responses.get(query, 'Fine. Be that way.')
