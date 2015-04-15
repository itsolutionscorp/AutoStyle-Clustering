import re
from collections import Counter


class Phrase(object):
    """ Phrase class """
    def __init__(self, phrase):
        """ Initialize object and perform sanitization

        @param phrase: input phrase string
        """
        # remove non alpha characters
        phrase = re.sub(r'[^\w]', ' ', phrase)
        self.phrase = phrase.lower()

    def word_count(self):
        """ Count the number of words' occurrences in a string

        @return: dict of words and their number of occurrences
        """
        words = self.phrase.split()
        return dict(Counter(words))
