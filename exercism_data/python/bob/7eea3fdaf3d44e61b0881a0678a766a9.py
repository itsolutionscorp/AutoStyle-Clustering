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
        is_upper = sentence == sentence.upper()
        contains_alphas = re.match(ur'.*[a-zA-Z]+', sentence) is not None
        return is_upper and contains_alphas

    def is_interrogative(self, sentence):
        return sentence.endswith('?')
