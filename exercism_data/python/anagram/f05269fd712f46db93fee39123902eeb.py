class Anagram:

    def __init__(self, word):
        self.word = word

    def match(self, list):
        return filter(self.match_canonical, list)

    def canonical(self, word):
        letters = list(word)
        letters.sort()
        return ''.join(letters)

    def match_canonical(self, word):
        return self.canonical(word.lower()) == self.canonical(self.word.lower()) and self.word.lower() != word.lower()
