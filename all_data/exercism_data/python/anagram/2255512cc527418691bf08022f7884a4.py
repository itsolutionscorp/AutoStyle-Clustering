def detect_anagrams(word, answerList):
    resultList = []
    word = word.lower()
    sortedWord = ''.join(sorted(word))
    
    for x in range(len(answerList)):
        lowerAnswer = answerList[x].lower()
        if sortedWord == ''.join(sorted(lowerAnswer)):
            if not(word == lowerAnswer):                
                resultList.append(answerList[x])
                
    return resultList
