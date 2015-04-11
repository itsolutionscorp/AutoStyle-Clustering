import re
from collections import Counter

not_word_pattern = re.compile('[^a-z0-9]+')


class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        phrase = not_word_pattern.sub(' ', self.phrase.lower())
        words = phrase.strip().split()
        return Counter(words)
