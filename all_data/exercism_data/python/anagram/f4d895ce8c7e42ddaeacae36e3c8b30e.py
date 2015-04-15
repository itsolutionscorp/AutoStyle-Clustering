def detect_anagrams(word, possible_list):
    return [testword for testword in possible_list
            if sorted(word.lower()) == sorted(testword.lower())
            and word.lower() != testword.lower()]
