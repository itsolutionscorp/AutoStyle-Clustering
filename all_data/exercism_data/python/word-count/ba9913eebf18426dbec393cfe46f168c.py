import string
from collections import Counter

class Phrase:
    def __init__(self, input_phrase):
        """Keep the constructor unit test happy. May as well create member phrase."""
        self.phrase = input_phrase

    def word_count(self):
        """Count the words in the phrase input into the constructor."""
        word_counter = Counter()
        # Search for words after stripping punctuation and separating by spaces
        for word in self.phrase.translate(None, string.punctuation).split():
            # Increment counts for words in their lower cased form
            word_counter[word.lower()] += 1
        return word_counter
