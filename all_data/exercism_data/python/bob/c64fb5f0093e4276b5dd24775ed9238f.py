class Utterance:
    def __init__(self, text):
        self.text = text

    def is_silent(self):
        return self.text == ''

    def is_shouty(self):
        return self.text.isupper()

    def is_question(self):
        return self.text.endswith('?')

class Bob:
    def hey(self, utterance_text):
        utterance = Utterance(utterance_text)
        if utterance.is_silent():
            return 'Fine. Be that way.'
        elif utterance.is_shouty():
            return 'Woah, chill out!'
        elif utterance.is_question():
            return 'Sure.'
        else:
            return 'Whatever.'
