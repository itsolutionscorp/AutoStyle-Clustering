def detect_anagrams(word, anag_list):
    anag_list2 = []
    wordlist = list(word.lower())
    wordlist.sort()
    for x in anag_list:
        if x.lower() == word.lower():
            continue
        y = list(x.lower())
        y.sort()
        if wordlist == y:
            anag_list2.append(x)
    return anag_list2
