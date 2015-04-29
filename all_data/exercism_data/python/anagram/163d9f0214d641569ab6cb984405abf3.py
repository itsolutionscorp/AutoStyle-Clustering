class Anagram:

    def __init__(self, word):
        self._word = word

    def match(self, candidates):
        return [c for c in candidates if self.is_match(c)]

    def is_match(self, candidate):
        if self._word == candidate:
            return False

        word_chars      = sorted(list(self._word.lower()))
        candidate_chars = sorted(list(candidate.lower()))

        return word_chars == candidate_chars
