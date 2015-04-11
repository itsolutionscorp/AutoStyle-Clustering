from collections import defaultdict
import string


class Phrase(object):
    excludes = set(string.punctuation)

    def __init__(self, sentence):
        self.sentence = sentence
        self.words = defaultdict(int)

    def word_count(self):
        for word in self.sentence.split():
            key = self.format_word(word)
            if key:
                self.words[key] += 1

        return self.words

    def format_word(self, word):
        return self.only_alpha_characters(word.lower())

    def only_alpha_characters(self, word):
        return ''.join(c for c in word if c not in self.excludes)
