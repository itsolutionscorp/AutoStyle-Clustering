import re

class Phrase:

    def __init__(self, phrase):
        self.phrase = self._parse(phrase)

    def word_count(self):
        counts = {}
        for word in self.phrase:
            if word:
                counts[word] = counts.get(word,0) + 1
        return counts

    def _parse(self, phrase):
        return self._strip_non_words(str.split(phrase))

    def _strip_non_words(self, words):
        return [re.sub('[^a-z0-9]*', '', word.lower()) for word in words]
