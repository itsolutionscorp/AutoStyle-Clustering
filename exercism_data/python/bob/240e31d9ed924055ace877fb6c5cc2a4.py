# -*- coding: utf-8 -*-


class Bob(object):
    """Bob a lackadaisical teenager.

    In conversation, his responses are very limited.
    """
    def hey(self, phrase):
        phrase = Phrase(phrase)

        if phrase.is_silence():
            return 'Fine. Be that way!'

        if phrase.is_yelld():
            return 'Woah, chill out!'

        if phrase.is_asked():
            return 'Sure.'
        else:
            return 'Whatever.'


class Phrase(object):
    def __init__(self, raw_phrase):
        self.phrase = '' if raw_phrase is None else str(raw_phrase)

    def is_silence(self):
        return not self.phrase or self.phrase.isspace()

    def is_yelld(self):
        return self.phrase.isupper()

    def is_asked(self):
        return self.phrase.endswith('?')
