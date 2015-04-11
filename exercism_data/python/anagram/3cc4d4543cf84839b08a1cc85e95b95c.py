from collections import Counter


class Anagram(object):
    @staticmethod
    def normalize(word):
        wl = word.lower()
        return wl, Counter(wl)

    def __init__(self, target):
        self._target = target
        self._target_lower, self._target_counter = \
            self.normalize(target)

    def match(self, candidates):
        return [s for s in candidates if self.match_one(s)]

    def match_one(self, word):
        wl, c = self.normalize(word)
        return wl != self._target_lower and c == self._target_counter
