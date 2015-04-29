import string

class Anagram(object):
    def __init__(self, v):
        self.tag = "".join(sorted([l for l in v.lower() if l in string.letters]))
        self.v = v

    def match(self, x):
        return [m for m in x if  self.__class__(m).tag == self.tag and self.__class__(m).v != self.v]
