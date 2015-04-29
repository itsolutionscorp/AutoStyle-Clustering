class Anagram:
    def __init__(self, word):
        self._word = word.lower()

    def match(self, words):
        return [word for word in words if self.is_anagram(word.lower())]

    def is_anagram(self, word):
        w = self._word

        if w == word:
            return False

        for c in word:
            if not c in w:
                return False
            w = w.replace(c, '', 1)
        return not w
