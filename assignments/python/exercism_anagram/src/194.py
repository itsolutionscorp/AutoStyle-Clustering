def detect_anagrams(word, list_to_test):
    """tests if list contains anagrams of word"""
    true_anagrams = []
    #split word to list of lowercase chars
    word_list = sorted(list(word.lower()))
    
    #check every word in list by sorting and comparing
    for word_to_test in list_to_test:
        if word_to_test.lower() != word.lower():
            word_to_test_split = sorted(list(word_to_test.lower()))
            if word_to_test_split == word_list:
                true_anagrams.append(word_to_test)
    
    return true_anagrams
