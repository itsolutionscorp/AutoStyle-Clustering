LETTER_VALUES = {"AEIOULNRST": 1, "DG": 2, "BCMP": 3,
                 "FHVWY": 4, "K": 5, "JX": 8, "QZ": 10}


class Word(object):

    """A scrabble word."""

    def __init__(self, word):
        """Create new scrabble word."""
        self.word = word.upper()

    def score(self):
        """Return scrabble score for this word."""
        counter = 0
        for letter in self.word:
            for letters, value in LETTER_VALUES.items():
                if letter in letters:
                    counter += value
        return counter
