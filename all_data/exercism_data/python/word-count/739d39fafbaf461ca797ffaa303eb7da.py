from collections import Counter
import re

WORD_REGEX = re.compile("\w+", re.UNICODE)


class Phrase(object):

    def __init__(self, phrase):
        self.phrase = phrase

    def words(self):
        """Return a list of the words in the phrase."""
        return re.findall(WORD_REGEX, self.phrase)

    def word_count(self):
        """Return a dictionary where the keys are the words in the phrase,
        normalized to lower case, and the values are the frequency of these
        words in the phrase.
        """
        return Counter(word.lower() for word in self.words())
