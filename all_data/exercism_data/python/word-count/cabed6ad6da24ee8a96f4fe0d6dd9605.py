import re

class Phrase:

    def __init__(self, sentence=''):
        sentence = re.sub('[^a-zA-Z0-9\?\n]', ' ', sentence);
        words = sentence.split()
        self.wordsCount = {}
        for key in words:
            self.wordsCount[key.lower()] = self.wordsCount.get(key.lower(), 0) + 1


    def word_count(self):
        return self.wordsCount
