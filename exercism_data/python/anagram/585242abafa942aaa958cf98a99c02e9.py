def detect_anagrams(word, words):
    anagrams = []
    for w in words:
        if is_anagram(w, word):
            anagrams.append(w)
    return anagrams

def is_anagram(word1, word2):
    word1 = word1.lower()
    word2 = word2.lower()
    if len(word1) != len(word2) or word1 == word2:
        return False
    for c in word1:
        if c in word2:
            word2 = word2.replace(c, '', 1)
    return word2 == ''
