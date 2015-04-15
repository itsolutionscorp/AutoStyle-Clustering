class Anagram(object):
    def __init__(self, word):
        self.word =  word
        self.normalized = self._sorted(word)

    def match(self, candidates):
        sorted_cand = [(self._sorted(w),w) for w in candidates]
        matches = []
        for normalized, word in sorted_cand:
            if self._same(word, self.word):
                continue
            if self._is_match(normalized, self.normalized):
                matches.append(word)
        return matches 

    def _is_match(self, w1, w2):
        return w1 == w2

    def _same(self, w1, w2):
        return w1.lower() == w2.lower()

    def _sorted(self, w):
        return ''.join(sorted(w.lower()))
