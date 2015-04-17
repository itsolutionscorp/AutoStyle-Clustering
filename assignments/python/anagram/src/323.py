def detect_anagrams(test_word, possible_anagrams):
    return [word for word in possible_anagrams if sorted(test_word.lower()) == sorted(word.lower()) and test_word.lower() != word.lower()]
