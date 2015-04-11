# -*- coding: utf-8 -*-
class Bob():
    def hey(self, word):
        if self._silence(word):
            return "Fine. Be that way!"
        word = word.decode('ISO-8859-1')
        if self._screaming(word):
            return "Woah, chill out!"
        elif self._question(word):
            return "Sure."
        else:
            return "Whatever."

    def _silence(self, word):
        return not word or not word.strip()

    def _screaming(self, word):
        return word.isupper()

    def _question(self, word):
        return word.endswith('?')
