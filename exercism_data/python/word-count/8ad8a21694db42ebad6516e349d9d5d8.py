import string
import re

class Phrase:
    def __init__(self, input_phrase):
        """Keep the constructor unit test happy. May as well create member phrase."""
        self.phrase = input_phrase

    def word_count(self):
        """Count the words in the phrase input into the constructor."""
        word_counter = {}
        # Search for words after stripping punctuation and separating by spaces
        for word in self.phrase.translate(None, string.punctuation).split():
            normalized_word = word.lower()
            if normalized_word in word_counter:
                # increment count for repeated word
                word_counter[normalized_word] += 1
            else:
                # first time we have encountered a word
                word_counter[normalized_word] = 1
        return word_counter
