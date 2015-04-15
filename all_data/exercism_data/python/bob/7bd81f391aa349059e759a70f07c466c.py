# Python itself doesn't support the full range of character classes for
# unicode. To keep the code more readable we are going to use the regex
# drop-in replacement module here.
import regex as re


class Bob(object):
    def hey(self, message):
        if self._is_nonsense(message):
            return 'Fine. Be that way!'
        if self._is_shouting(message):
            return 'Woah, chill out!'
        if self._is_question(message):
            return 'Sure.'
        return 'Whatever.'

    def _is_nonsense(self, message):
        if message is None or len(message.strip()) == 0:
            return True
        return False

    def _is_question(self, message):
        if message.endswith('?'):
            return True
        return False

    def _is_shouting(self, message):
        words = message.split()

        # If a word contains a lower-case character, we can leave right away.
        lower_case = re.compile(ur'\p{Ll}')
        for word in words:
            match = lower_case.search(word)
            if match:
                return False

        # Next we operate only on the part of a "word" that contains
        # characters in order to get rid of any trailing nonsense. If the
        # character-part consists only of uppercase characters, it is
        # shouting.
        all_caps = re.compile(ur'^\p{Lu}+([^\p{L}]*?)$')
        for word in words:
            match = all_caps.match(word)
            if match is not None:
                return True
        return False
