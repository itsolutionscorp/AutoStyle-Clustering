import re

class Phrase:

    def __init__(self, phrase):
        self._words = re.findall('\w+', phrase.lower())

    def word_count(self):
        res = {}
        for word in self._words:
            if not word in res:
                res[word] = 0
            res[word] += 1
        return res
