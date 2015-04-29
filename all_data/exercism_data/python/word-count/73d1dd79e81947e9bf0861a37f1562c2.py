import string

class Phrase:
    """ Holds a word phrase, provides a word count function that 
    ignores punctuation, and converts all words to lowercase"""

    def __init__(self, phrase):
        # Preprocess string to remove punctuation and make everything lowercase
        self.phrase = phrase.translate(None, string.punctuation).lower()

    def word_count(self):
        words = self.phrase.split()
        counts = {}
        for word in words:
            if word in counts:
                counts[word] = counts[word] + 1
            else: 
                counts[word] = 1
        return counts
