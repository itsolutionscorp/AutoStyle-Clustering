import re

class Bob:
    def hey(self, utterance):
        if utterance == '':
            return 'Fine. Be that way.'
        elif re.match(r'^[A-Z]+$', utterance):
            return 'Woah, chill out!'
        elif utterance.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
