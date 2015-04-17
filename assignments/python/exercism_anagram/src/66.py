def detect_anagrams(word, wordList):
    sort = lambda x: ''.join(sorted(list(x.lower())))
        
    alp = sort(word)
    alpList = map(sort, wordList)
    ret = []
    for i, w in enumerate(alpList):
        if w == alp and word.lower() != wordList[i].lower():
            ret.append(wordList[i])
    return ret
