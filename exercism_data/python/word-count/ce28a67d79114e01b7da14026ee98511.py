from string import punctuation


class Phrase(object):

    """A couple of words."""

    def __init__(self, phrase):
        """Create a new phrase from given string."""
        self.phrase = phrase

    def clean(self):
        """Return phrase without punctuation."""
        return self.phrase.translate(None, punctuation)

    def words(self):
        """Return list of words in this phrase."""
        return self.clean().split()

    def word_count(self):
        """Count the words in this phrase, ignoring case."""
        frequencies = {}
        for word in [w.lower() for w in self.words()]:
            if word in frequencies:
                frequencies[word] += 1
            else:
                frequencies[word] = 1
        return frequencies
