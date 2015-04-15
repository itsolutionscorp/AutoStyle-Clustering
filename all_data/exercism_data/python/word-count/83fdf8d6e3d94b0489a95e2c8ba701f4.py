import re

class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase
        self.words = re.sub(r'[^\w]+', r' ', phrase).lower().split()

    def word_count(self):
        counts = {}
        for word in self.words:
            if word not in counts:
                counts[word] = 1
            else:
                counts[word] += 1

        return counts
