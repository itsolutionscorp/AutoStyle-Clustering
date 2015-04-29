from collections import defaultdict
from string import punctuation

class Phrase(object):
    def __init__(self, inPhrase):
        self.counts = defaultdict(int)
        
        inPhrase = inPhrase.lower() # Normalise Case
        for char in punctuation:    # Remove punctuation
            inPhrase = inPhrase.replace(char, '')

        for word in inPhrase.split():
            self.counts[word] += 1

    def word_count(self):
        return self.counts
