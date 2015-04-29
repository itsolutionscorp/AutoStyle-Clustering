import string

class Phrase(object):
    """Provides word count stats for a phrase"""

    def __init__(self, phrase):
        """Sets the phrase"""
        self.phrase = phrase

    def word_count(self):
        """Generates word counts for words (lower case, no punctuation)"""

        words = self.phrase.split() # split words on default word boundaries

        # translate removes punctuation only, normalizes to lower case
        normalized_words = [self.normalize_word(w) for w in words]

        # removes empty strings after stripping punctuation
        filtered_words = [w for w in normalized_words if w]

        # turn words into counted words by zero default and increment
        result = {}
        for word in filtered_words:
            result[word] = result.get(word,0)+1
        return result

    def normalize_word(self, word):
        """Strips the punctuation from string"""
        # translates via 'None' (no change), provides punctuation for deletion
        return word.translate(None, string.punctuation).lower()
