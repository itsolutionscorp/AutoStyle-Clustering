from collections import defaultdict
import string


class Phrase:
    strip_punctuation = str.maketrans('', '', string.punctuation)

    def __init__(self, string):
        self.string = string
        self.words = defaultdict(lambda: 0)

    def word_count(self):
        [self.increment_word_count(word) for word in self.string.split()]
        return self.words

    def increment_word_count(self, word):
        clean = word.lower().translate(self.strip_punctuation)
        if clean:
            self.words[clean] += 1
