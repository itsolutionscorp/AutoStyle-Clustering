class Bob(object):
    def hey(self, message):
        message = message or ''
        phrase = Phrase(message)

        if phrase.is_silence():
            return 'Fine. Be that way!'
        elif phrase.is_shout():
            return 'Woah, chill out!'
        elif phrase.is_question():
            return 'Sure.'
        else:
            return 'Whatever.'


class Phrase(object):
    def __init__(self, message):
        self.phrase = message

    def is_silence(self):
        return not self.phrase or self.phrase.strip() == ''

    def is_question(self):
        return self.phrase.endswith('?')

    def is_shout(self):
        return self.phrase.isupper()
