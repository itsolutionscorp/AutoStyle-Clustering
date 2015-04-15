from collections import Counter
import string


class Phrase:
    STRIP_PUNCTUATION = str.maketrans('', '', string.punctuation)

    def __init__(self, string):
        self.string = string

    def word_count(self):
        return Counter(self.extract_single_words())

    def extract_single_words(self):
        for word in self.string.split():
            clean = word.lower().translate(Phrase.STRIP_PUNCTUATION)
            if clean:
                yield clean
