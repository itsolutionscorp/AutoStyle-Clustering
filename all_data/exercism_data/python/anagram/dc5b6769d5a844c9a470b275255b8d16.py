from collections import Counter


class Anagram(object):
    def __init__(self, target):
        self.target = target

    @staticmethod
    def normalize(word):
        wl = word.lower()
        return wl, Counter(wl)

    @property
    def target(self):
        return self._target

    @target.setter
    def target(self, new_val):
        self._target = new_val
        self._target_lower, self._target_counter = \
            self.__class__.normalize(new_val)

    def match(self, candidates):
        return [s for s in candidates if self.match_one(s)]

    def match_one(self, word):
        wl, c = self.__class__.normalize(word)
        return wl != self._target_lower and c == self._target_counter
