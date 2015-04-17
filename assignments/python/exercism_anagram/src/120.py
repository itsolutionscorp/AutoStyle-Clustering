def detect_anagrams(word, wordlist):
    return [w for w in wordlist if sorted(word.lower()) == sorted(w.lower())\
                                     and word.lower() != w.lower()]
