def detect_anagrams(given_word, candidates):
    anagrams = []
    for test_word in candidates:
        lc_given = given_word.lower()
        lc_test = test_word.lower()
        if lc_given != lc_test and sorted(lc_given) == sorted(lc_test):
            anagrams.append(test_word)
    return anagrams
