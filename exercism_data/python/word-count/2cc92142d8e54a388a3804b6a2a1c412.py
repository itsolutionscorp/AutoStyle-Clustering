import string
import re

class Phrase:
    def __init__(self, input_phrase):
        """Keep the constructor unit test happy. May as well create member phrase."""
        self.phrase = input_phrase

    def simplified_phrase(self):
        """Prepare and butcher phrase into a string of single-space separated words."""
        # Remove punctuation
        new_phrase = re.sub('[%s]' % re.escape(string.punctuation), '', self.phrase)
        # Collapse multiple adjacent spaces
        new_phrase = re.sub(' +', ' ', new_phrase)
        # Return value should be ready to be consumed by word_count()
        return new_phrase

    def word_count(self):
        """Count the words in the phrase input into the constructor."""
        word_counter = {}
        for word in self.simplified_phrase().split(' '):
            normalized_word = word.lower()
            if word_counter.has_key(normalized_word):
                # increment count for repeated word
                word_counter[normalized_word] += 1
            else:
                # first time we have encountered a word
                word_counter[normalized_word] = 1
        return word_counter
