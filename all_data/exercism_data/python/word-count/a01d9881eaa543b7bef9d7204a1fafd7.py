import re

class Phrase(object):
    "The fourth exercism exercise"

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        "Count words in a phrase"
        words = {}
        for word in re.split(r'\W+', self.phrase):
            if word:
                word = word.lower()
                count = words.get(word, 0)
                words[word] = count + 1
        return words
