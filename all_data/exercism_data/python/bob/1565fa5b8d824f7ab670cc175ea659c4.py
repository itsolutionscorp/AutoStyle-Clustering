# -*- coding: utf-8 -*-

class Bob:

    def hey(self, s: str) -> str:
        if self._is_silence(s):
            return "Fine. Be that way!"
        elif self._is_yelling(s):
            return "Woah, chill out!"
        elif self._is_question(s):
            return "Sure."
        else:
            return "Whatever."

    def _is_silence(self, s: str) -> bool:
        return not s.strip()

    def _is_yelling(self, s: str) -> bool:
        alpha = ''.join(c for c in s if c.isalpha())
        return alpha and alpha.upper() == alpha

    def _is_question(self, s: str) -> bool:
        return s.endswith('?')
