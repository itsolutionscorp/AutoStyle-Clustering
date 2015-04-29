def detect_anagrams(word, answerList):
    resultList = []
    sortedWord = ''.join(sorted(word.lower()))
    word = word.lower()
    
    for x in range(len(answerList)):
        lowerAnswer = answerList[x].lower()
        if sortedWord == ''.join(sorted(lowerAnswer)):
            if not(word == lowerAnswer):                
                resultList.append(answerList[x])
                
    return resultList
