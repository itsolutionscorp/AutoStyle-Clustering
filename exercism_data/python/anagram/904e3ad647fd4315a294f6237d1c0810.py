def _get_1gram_counts(word):
    grams = {}
    for char in word:
        if char in grams:
            grams[char] += 1
        else:
            grams[char] = 1
    return grams

def detect_anagrams(source_word, test_words):
    source_word = source_word.lower()
    source_grams = _get_1gram_counts(source_word)
    return [t for t in test_words
            if source_word != t.lower()
            and source_grams == _get_1gram_counts(t.lower())]
