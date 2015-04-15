def signature(word):
    return sorted(word.lower())

class Anagram(object):
    def __init__(self, word):
        self.target = word
        self.signature = signature(word)

    def match(self, words):
        return [word for word in words
                if word != self.target
                and signature(word) == self.signature]
