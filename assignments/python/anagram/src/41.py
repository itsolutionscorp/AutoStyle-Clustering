def detect_anagrams(word, wordlist):
    retList = []
    for entry in wordlist:
        lowentry = entry.lower()
        word = word.lower()
        if lowentry == word:
            continue
        ordListForWord = []
        ordListForWordList = []
        for i in range(65, 122 + 1):
            ordListForWord.append(0)
            ordListForWordList.append(0)
        for letter in word:
            ordListForWord[ord(letter) - 65] += 1;
        for letter in lowentry:
            ordListForWordList[ord(letter) - 65] += 1;
        if (ordListForWordList == ordListForWord):
            retList.append(entry)
    return retList
