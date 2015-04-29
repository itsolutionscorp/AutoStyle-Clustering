import re


class Bob():

    def __init__(self):
        self.response_map = {
            "shouting": "Woah, chill out!",
            "question": "Sure.",
            "statement": "Whatever.",
            "silence": "Fine. Be that way!"
        }

    def hey(self, phrase):
        communication = self.parsePhrase(phrase)
        return self.response_map[communication]

    def parsePhrase(self, phrase):

        if phrase == '' or re.match('\s+', phrase):
            return 'silence'

        elif phrase == phrase.upper() and re.search('[A-z]', phrase):
            return 'shouting'

        elif phrase.endswith('?'):
            return 'question'
        else:
            return 'statement'
