import re

class Bob:
    def hey(self, sentence=''):
        if self.is_empty(sentence):
            return 'Fine. Be that way!'
        if self.is_exclamatory(sentence):
            return 'Woah, chill out!'
        if self.is_interrogative(sentence):
            return 'Sure.'
        return 'Whatever.'

    def is_empty(self, sentence):
        return len(sentence.strip()) == 0

    def is_exclamatory(self, sentence):
        return sentence == sentence.upper() and re.match(ur'.*[a-zA-Z]+', sentence) is not None

    def is_interrogative(self, sentence):
        return sentence.endswith('?')
