def detect_anagrams(word, possible_anagrams):
    """tests if list contains anagrams of word"""

    true_anagrams = []

    #split word to list of lowercase chars
    word_chars = sorted(word.lower())
    
    #check every word in possible anagrams by sorting and comparing
    for word_to_test in possible_anagrams:
        if word_to_test.lower() != word.lower():
            word_to_test_chars = sorted(word_to_test.lower())
            if word_to_test_chars == word_chars:
                true_anagrams.append(word_to_test)
    
    return true_anagrams
