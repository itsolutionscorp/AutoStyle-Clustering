import re

class Phrase(object):
    word_pattern = re.compile("[a-zA-Z0-9]+")

    def __init__(self, text):
        self.count = {}

        for m in self.word_pattern.finditer(text):
            word = m.group(0).lower()
            self.count[word] = self.count.get(word, 0) + 1

    def word_count(self):
        return self.count
