class Anagram:

    def __init__(self, word):
        self.word = word

    def match(self, list):
        return filter(self.valid_anagram, list)

    def canonical(self, word):
        letters = list(word)
        letters.sort()
        return ''.join(letters)

    def valid_anagram(self, word):
        return self.match_canonical(word) and self.different_word(word)

    def match_canonical(self, word):
        return self.canonical(word.lower()) == self.canonical(self.word.lower())

    def different_word(self, word):
        return self.word.lower() != word.lower()
