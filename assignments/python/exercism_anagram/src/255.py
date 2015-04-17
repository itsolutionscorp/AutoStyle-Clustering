def detect_anagrams(original_word, words_to_check):
    letters = sorted(original_word.lower())
    detected = list()
    for word in words_to_check:
        if letters == sorted(word.lower()) and not word.lower() == original_word.lower():
            detected.append(word)
    return detected
