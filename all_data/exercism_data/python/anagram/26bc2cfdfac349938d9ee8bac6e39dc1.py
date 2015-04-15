def alphabetize(word):
    return ''.join(sorted(word.lower()))

def detect_anagrams(test_word, possible_anagrams):
    return [word for word in possible_anagrams if alphabetize(test_word) == alphabetize(word) and test_word.lower() != word.lower()]
