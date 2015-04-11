import re

class Bob:
    def hey(self, sentence=''):
        if self.qualify(sentence) == 'empty':
            return 'Fine. Be that way!'
        if self.qualify(sentence) == 'yelling':
            return 'Woah, chill out!'
        if self.qualify(sentence) == 'question':
            return 'Sure.'
        return 'Whatever.'

    def qualify(self, sentence):
        if len(sentence.strip()) == 0:
            return 'empty'
        elif sentence == sentence.upper() and re.match(ur'.*[a-zA-Z]+', sentence) is not None:
            return 'yelling'
        elif sentence.endswith('?'):
            return 'question'
