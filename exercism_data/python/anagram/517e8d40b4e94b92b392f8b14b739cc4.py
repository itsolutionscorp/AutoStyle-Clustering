class Anagram:
    def __init__(self, word):
        self.word = word.lower()
        self.chars = sorted(self.word)

    def is_anagram_to(self, other):
        return self.chars == other.chars and self.word != other.word


def detect_anagrams(word, candidates):
    reference = Anagram(word)
    return [candidate
            for candidate in candidates
            if Anagram(candidate).is_anagram_to(reference)]
