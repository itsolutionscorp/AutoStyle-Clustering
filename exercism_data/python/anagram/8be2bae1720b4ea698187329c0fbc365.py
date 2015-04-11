def detect_anagrams(word, poss_anagrams):
    anagram_list = []

    for cand in poss_anagrams:
        if cand.lower() == word.lower():
            continue        
        is_anagram = True
        testcand = cand
        for char in word.lower():
            if testcand.lower().find(char) == -1:
                is_anagram = False
            else:
                testcand = testcand[:testcand.lower().find(char)] + testcand[testcand.lower().find(char)+1:]
        if is_anagram and testcand == '':
            anagram_list.append(cand)
    return anagram_list
