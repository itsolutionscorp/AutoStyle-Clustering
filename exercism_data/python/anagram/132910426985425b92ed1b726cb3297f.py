def detect_anagrams(word, wordlist):
    return [w for w in wordlist if isAnagram(w, word)]

def isAnagram(a, b):
    a = a.lower()
    b = b.lower()
    if a == b or len(a) != len(b):
        return False
    return all(a.count(ch) == b.count(ch) for ch in a)
