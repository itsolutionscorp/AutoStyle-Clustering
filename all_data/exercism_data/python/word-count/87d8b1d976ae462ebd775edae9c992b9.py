import string
import collections

class Phrase(object):
    """Provides word count stats for a phrase"""

    def __init__(self, phrase):
        """Sets the phrase"""
        self.phrase = phrase

    def word_count(self):
        """Generates word counts for words (lower case, no punctuation)"""

        # split words on default word boundaries for words list
        words = self.phrase.split() 

        # translate removes punctuation only, normalizes to lower case
        normalized_words = [self.normalize_word(w) for w in words]

        # removes empty strings after stripping punctuation
        filtered_words = [w for w in normalized_words if w]

        # sets up default dictionary, so all entries are 0
        word_counts = collections.defaultdict(int) #{}

        # define word counting function for use in reduce
        def count_word(dictionary, word):
            dictionary[word] = dictionary[word] + 1
            return dictionary

        # count words into dictionary from word list
        reduce(count_word, filtered_words, word_counts)

        return word_counts


    def normalize_word(self, word):
        """Strips the punctuation from string"""
        # translates via 'None' (no change), provides punctuation for deletion
        return word.translate(None, string.punctuation).lower()
