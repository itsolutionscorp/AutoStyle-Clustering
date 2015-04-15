import re

class Phrase(object):
    def __init__(self, phrase):
        self._phrase = phrase

    def word_count(self):
        count = {}
        for word in map(lambda match: match.group(0),
                        re.finditer(r'\w+', self._phrase.lower())):
            if word in count:
                count[word] += 1
            else:
                count[word] = 1
        return count
