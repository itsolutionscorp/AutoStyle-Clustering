"""A scrabble score."""

__all__ = ["Word"]

from collections import defaultdict


class Word(str):
    """str with a scrabble score."""

    __char_to_score = defaultdict(int, {
        "a": 1, "b": 3, "c": 3, "d": 2, "e": 1, "f": 4, "g": 2, "h": 4, "i": 1,
        "j": 8, "k": 5, "l": 1, "m": 3, "n": 1, "o": 1, "p": 3, "q": 10,
        "r": 1, "s": 1, "t": 1, "u": 1, "v": 4, "w": 4, "x": 8, "y": 4, "z": 10
    })

    def __init__(self, word):
        super(Word, self).__init__(word)
        self._score = None

    def __repr__(self):
        cls = self.__class__
        return "{!s}.{!s}({!r})".format(
            cls.__module__, cls.__name__, str(self))

    def score(self):
        """Return the score."""
        if self._score is None:
            self._score = \
                sum(Word.__char_to_score[char] for char in self.lower())
        return self._score
