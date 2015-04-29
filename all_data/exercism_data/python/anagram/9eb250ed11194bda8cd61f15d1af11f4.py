def detect_anagrams(word, anagrams):
    return [anagram for anagram in anagrams if _are_anagrams(word, anagram)]

def _sort_word(word):
    letters = [c for c in word.lower()]
    letters.sort()
    return letters

def _are_anagrams(word1, word2):
    return word1.lower() != word2.lower() and _sort_word(word1) == _sort_word(word2)
