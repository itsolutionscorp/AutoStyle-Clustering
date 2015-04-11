import re

class Phrase(object):
    def __init__(self, phrase):
        self._phrase = phrase

    def word_count(self):
        count = {}
        def match_to_word(m):
            return m.group(0).lower()
        for word in map(match_to_word, re.finditer(r'\w+', self._phrase)):
            if word in count:
                count[word] += 1
            else:
                count[word] = 1
        return count
