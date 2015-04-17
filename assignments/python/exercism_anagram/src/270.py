import collections
def detect_anagrams(word, word_list):
    word = word.upper()
    c = collections.Counter(word)
    return [w for w in word_list if w.upper() != word and collections.Counter(w.upper()) == c]
