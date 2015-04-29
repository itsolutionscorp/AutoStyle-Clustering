class Anagram:
    def __init__(self, s):
        self.sort=sorted(s.lower())
        self.orig=s
    def match(self, l):
        return filter(lambda w: sorted(w.lower())==self.sort and w!=self.orig, l)
