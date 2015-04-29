from collections import Counter
import string


class Phrase:
    STRIP_PUNCTUATION = str.maketrans('', '', string.punctuation)

    def __init__(self, string):
        self.string = string
        self.words = Counter()

    def word_count(self):
        self.words.update((self.clean(word) for word in self.string.split()))
        del self.words['']
        return self.words

    def clean(self, word):
        return word.lower().translate(Phrase.STRIP_PUNCTUATION)
