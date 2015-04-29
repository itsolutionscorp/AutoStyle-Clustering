class Phrase:
    def __init__(self, text):
        self.text = text

    def is_silence(self):
        return len(self.text.strip()) == 0

    def is_shouting(self):
        return self.text == self.text.upper()

    def is_question(self):
        return self.text.endswith('?')

class Bob:
    def hey(self, text):
        phrase = Phrase(text)

        if phrase.is_silence():
            return 'Fine. Be that way.'
        elif phrase.is_shouting():
            return 'Woah, chill out!'
        elif phrase.is_question():
            return 'Sure.'
        else:
            return 'Whatever.'
