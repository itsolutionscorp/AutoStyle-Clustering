import collections
def detect_anagrams(word, words):
    word = word.lower()
    chars = collections.Counter(word)
    return [w for w in words if w.lower() != word and collections.Counter(w.lower()) == chars]
