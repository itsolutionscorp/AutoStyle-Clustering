def is_anagram(word1, word2):
    w1 = word1.lower() 
    w2 = word2.lower()
    if w1 == w2:
        return False
    return sorted(w1) == sorted(w2)


def detect_anagrams(word, cand_list):
    return [c for c in cand_list if is_anagram(word, c)]
