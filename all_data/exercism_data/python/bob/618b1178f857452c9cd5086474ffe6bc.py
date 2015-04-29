class Bob(object):
    def hey(self, message):
        message = self._validate(message)
        phrase  = Phrase(message)
        return self._answer_to(phrase)

    def _answer_to(self, phrase):
        if phrase.is_silence():
            return 'Fine. Be that way!'
        elif phrase.is_shout():
            return 'Woah, chill out!'
        elif phrase.is_question():
            return 'Sure.'
        else:
            return 'Whatever.'

    def _validate(self, message):
        if message is True:
            raise ValueError("message can't be {}.".format(message))
        return message or ''


class Phrase(object):
    def __init__(self, message):
        self.phrase = message

    def is_silence(self):
        return not self.phrase or self.phrase.strip() == ''

    def is_question(self):
        return self.phrase.endswith('?')

    def is_shout(self):
        return self.phrase.isupper()
