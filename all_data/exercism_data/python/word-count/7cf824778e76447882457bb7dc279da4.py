import re

class Phrase(object):
    def __init__(self, text):
        self.words = self._words(text)

    def word_count(self):
        def increment_count(counts, x):
            counts[x] = counts.get(x, 0) + 1
            return counts
        return reduce(increment_count, self.words, {})

    def _words(self, text):
        return re.findall(r'\w+', text.lower())
