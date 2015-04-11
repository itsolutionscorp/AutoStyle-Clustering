def detect_anagrams(test_word, word_list):
    sorted_test_word = list(test_word.lower())
    sorted_test_word.sort()
    anagram_list = []
    for word in word_list:
        if (len(word) != len(test_word)) or (word.lower() == test_word.lower()):
            continue
        sorted_word = list(word.lower())
        sorted_word.sort()
        if sorted_word == sorted_test_word:
            anagram_list.append(word)
    return anagram_list
    
