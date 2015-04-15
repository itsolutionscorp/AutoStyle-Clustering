import re

class Phrase(object):
    def __init__(self, string):
        self.words = {}
        for word in string.split():
            key = re.sub("\W", "", word.lower())
            if key == "":
                continue
            elif key in self.words:
                self.words[key] += 1
            else:
                self.words[key] = 1

    def word_count(self):
        return self.words
