class Bob(object):

    def __init__(self):
        self.response_map = {
            "shouting": "Woah, chill out!",
            "question": "Sure.",
            "statement": "Whatever.",
            "silence": "Fine. Be that way!"
        }

    def hey(self, phrase):
        comm_type = self.parsePhrase(phrase)
        return self.response_map[comm_type]

    def parsePhrase(self, phrase):

        if not phrase.strip():
            return 'silence'

        elif phrase.isupper():
            return 'shouting'

        elif phrase.endswith('?'):
            return 'question'

        else:
            return 'statement'
