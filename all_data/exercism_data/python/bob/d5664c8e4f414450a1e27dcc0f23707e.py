class Bob(object):
    responses = {
            'A question?': 'Sure.',
            'SHOUTING': 'Woah, chill out!',
            'A statement.': 'Whatever.',
            }

    def hey(self, question):
        return self.responses.get(question, 'Fine. Be that way.')
