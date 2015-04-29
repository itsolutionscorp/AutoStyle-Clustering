def detect_anagrams(orig, words):
    lower_orig = orig.lower()
    sort = sorted(lower_orig)
    matches = []

    for word in words:
        lower_word = word.lower()
        if sorted(lower_word) == sort and lower_word != lower_orig:
            matches.append(word)

    return matches
