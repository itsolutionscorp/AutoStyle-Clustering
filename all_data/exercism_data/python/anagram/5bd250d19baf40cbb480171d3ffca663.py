class Anagram:
    def __init__(self, s):
        self.s = sorted(list(s.lower()))
        self.w = s
    def match(self, l):
        return [w for w in l if sorted(list(w.lower())) == self.s
                                and not self.w == w]
