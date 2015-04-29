import re

class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase

    def get_words(self):
        words = re.sub('\W+', ' ', self.phrase).split()
        return words

    def word_count(self):
        words = self.get_words()
        counts = {}
        for word in words:
            word = word.lower()
            if word in counts:
                counts[word] += 1
            else:
                counts[word] = 1
        return counts
