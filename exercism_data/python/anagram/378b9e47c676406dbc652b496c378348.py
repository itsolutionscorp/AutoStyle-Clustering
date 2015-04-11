class Anagram:
    def __init__(self, test_word):
        self.word = test_word
        self.sorted_word = self._sort_word(test_word)

    def match(self, candidates):
        matches = []
        for candidate in candidates:
            if candidate == self.word:
                continue
            if self._sort_word(candidate) == self.sorted_word:
                matches.append(candidate)
        return matches

    def _sort_word(self, word):
        return sorted(list(word.lower()))
