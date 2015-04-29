import string


class Phrase(object):
    """
    A string with some domain-specific characteristics
    """
    def __init__(self, phrase):
        self.phrase = phrase.strip()

    def __nonzero__(self):
        return bool(self.phrase)

    def is_yelling(self):
        """
        The phrase is considered to be "yelling" if the whole phrase is
        uppercase, and it contains at least one ascii character.
        """
        return self.phrase.isupper() and self._has_ascii_character()

    def is_question(self):
        """
        The phrase is a question if it ends with a '?'
        """
        return self.phrase.endswith('?')

    def _has_ascii_character(self):
        """
        The phrase contains at least one ascii character
        """
        return bool(set(self.phrase) & set(string.ascii_letters))


def hey(phrase):
    phrase = Phrase(phrase or '')

    if not phrase:
        return 'Fine. Be that way!'

    if phrase.is_yelling():
        return 'Woah, chill out!'

    if phrase.is_question():
        return 'Sure.'

    return 'Whatever.'
