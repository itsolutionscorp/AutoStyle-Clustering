def detect_anagrams(word, anagram_list):
    valid_list = []
    word = word.lower()
    for test_word in anagram_list:
        if test_word.lower() == word: continue
        letter_list = list(word)
        for letter in test_word:
            try:
                letter_list.remove(letter.lower())
            except ValueError:
                break
        else:
            if not letter_list:
                valid_list.append(test_word)
    return valid_list
