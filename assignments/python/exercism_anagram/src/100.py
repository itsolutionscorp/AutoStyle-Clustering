def detect_anagrams(word, possible_list):
    return_list = []
    [return_list.append(testword) for testword in possible_list
     if sorted(word.lower()) == sorted(testword.lower()) and word.lower() != testword.lower()]
    return return_list
