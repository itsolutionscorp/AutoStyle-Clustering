def detect_anagrams(source, words):
    not_equal = lambda w: w.lower() != source.lower()
    is_anagram = lambda w: sort_word(w) == sort_word(source)
    return filter(lambda t: all(f(t) for f in [not_equal, is_anagram]), words)

def sort_word(word):
    return ''.join(sorted(word.lower()))
