from collections import Counter


class Anagram:
    def __init__(self, s):
        self.s = s
        self.counter = Counter(s.lower())

    def match(self, candidates):
        return [s for s in candidates if
                Counter(s.lower()) == self.counter and s != self.s]
