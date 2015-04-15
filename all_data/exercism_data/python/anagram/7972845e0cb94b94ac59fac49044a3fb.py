class Anagram(object):
    def __init__(self, word):
        self.word = word.lower()
        self.letters = sorted(self.word)

    def _is_anagram(self, candidate):
        return (
            candidate != self.word and
            sorted(candidate.lower()) == self.letters
        )

    def match(self, candidates):
        return [
            candidate
            for candidate in candidates
            if self._is_anagram(candidate)
        ]
