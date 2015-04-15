def is_anagram(original, word):
    return original != word and sorted(original) == sorted(word)

def detect_anagrams(original, wordlist):
    return [w for w in wordlist if is_anagram(original.lower(), w.lower())]
