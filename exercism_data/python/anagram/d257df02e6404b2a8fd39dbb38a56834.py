from collections import Counter

def detect_anagrams(word, possible_anagrams):
    word = word.lower()
    def anagram_filter(w):
        w = w.lower()
        return w != word and Counter(w) == Counter(word)

    return filter(anagram_filter, possible_anagrams)
