"""Implements the rules in the README.md"""
class Anagram:
    """lower and order string and performs comparisons against other words"""
    def __init__(self, word):
        """Lowercases and orders a word"""
        self.word = word.lower()
        self.sorted = sorted(self.word)

    def is_anagram(self, other_word):
        """Validate same sorted string and NOT same word"""
        return self.sorted == other_word.sorted and self.word != other_word.word

def detect_anagrams(word, candidates):
    """Detect anagrams of given word in a list"""
    base_word = Anagram(word)
    matches = []
    for candidate in candidates:
        if Anagram(candidate).is_anagram(base_word):
            matches.append(candidate)
    return matches
