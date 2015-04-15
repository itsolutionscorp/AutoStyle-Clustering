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
    SILENCE = ''

    def __init__(self, raw_phrase):
        self.phrase = Phrase._normalize_silence(raw_phrase)

    def is_silence(self):
        return self.phrase is Phrase.SILENCE

    def is_yelld(self):
        return self.phrase == self.phrase.upper()

    def is_asked(self):
        return self.phrase.endswith('?')

    @staticmethod
    def _normalize_silence(phrase):
        return Phrase.SILENCE if phrase is None else phrase.strip()
