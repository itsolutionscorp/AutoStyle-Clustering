def detect_anagrams(word, possible_list):
    return_list = []
    for testword in possible_list:
        if sorted(word.lower()) == sorted(testword.lower()) and word.lower() != testword.lower():
            return_list.append(testword)
    return return_list
