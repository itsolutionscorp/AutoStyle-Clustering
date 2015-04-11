def detect_anagrams(word, options):
    return [o for o in options if is_anagram(word, o)]

def is_anagram(word, anagram):
    w = word.lower()
    a = anagram.lower()
    return a != w and sorted(w) == sorted(a)
