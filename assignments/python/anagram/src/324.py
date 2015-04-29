def detect_anagrams(word, words):
    detected = []
    word_lower = word.lower()
    word_sorted = sorted(word_lower)
    for w in words:
        test_word = w.lower()
        if word_lower != test_word and word_sorted == sorted(w.lower()):
            detected.append(w)
    return detected
