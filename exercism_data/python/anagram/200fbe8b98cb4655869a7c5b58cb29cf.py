from collections import Counter


def isanagram(w1, w2):
    w1, w2 = w1.lower(), w2.lower()
    return Counter(w1) == Counter(w2) and w1 != w2


def detect_anagrams(word, wordlist):
    return [w for w in wordlist if isanagram(w, word)]
