def detect_anagrams(word, anagrams):
    letters = sorted(word.lower())
    actual_anagrams = []
    for test_word in anagrams:
        if test_word.lower() == word.lower():
            continue
        test_letters = sorted(test_word.lower())
        if len(test_letters) != len(letters):
            continue
        for pair in zip(test_letters, letters):
            if pair[0] != pair[1]:
                break
        else:
            actual_anagrams.append(test_word)
    return actual_anagrams
