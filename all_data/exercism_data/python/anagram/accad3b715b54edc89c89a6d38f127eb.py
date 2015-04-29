class Anagram:
    def __init__(self, word):
        self.word = word

    def match(self, candidates):
        sorted_word = sorted(self.word.lower())
        matches = [word for word in candidates  if sorted(word.lower()) == sorted_word ]
        matches = [word for word in matches if not word.lower() == self.word.lower()]
        return matches
