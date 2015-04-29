class Anagram:
    def __init__(self, pattern):
        self.word = pattern.lower()
        self.schars = [x for x in self.word]
        self.schars.sort()

    def match(self, list):
        result = []
        for w in list:
            if self.word != w:
                lw = w.lower()
                chars = [x for x in lw]
                chars.sort()
                if chars == self.schars:
                    result.append(w)
        return result
