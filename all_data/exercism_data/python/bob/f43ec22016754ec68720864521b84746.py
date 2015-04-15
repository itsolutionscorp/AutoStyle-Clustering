import re

class Bob:
    def hey(self, string):

        if self._is_without(string):
            return "Fine. Be that way!"
        elif self._is_yell(string):
            return "Woah, chill out!"
        elif self._is_question(string):
            return "Sure."
        else:
            return "Whatever."

    def _is_question(self, string):
        return string.endswith("?")

    def _is_yell(self, string):
        return string.isupper()

    def _is_without(self, string):
        return not string.strip()
