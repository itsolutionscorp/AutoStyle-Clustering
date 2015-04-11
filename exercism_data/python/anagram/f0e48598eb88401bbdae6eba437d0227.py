def is_anagram(word1, word2):
    if word1 == word2:
        return False
    return sorted(word1) == sorted(word2)

def detect_anagrams(word, candidates):
    word = word.lower()
    return [anagram for anagram in candidates
            if is_anagram(word, anagram.lower())]
