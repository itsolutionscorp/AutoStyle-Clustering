def detect_anagrams(word, list):
    return [w for w in list if _is_anagram(w, word)]

def _is_anagram(word1, word2):
    return (
        sorted(word1.lower()) == sorted(word2.lower())
        and word1.lower() != word2.lower()
    )
