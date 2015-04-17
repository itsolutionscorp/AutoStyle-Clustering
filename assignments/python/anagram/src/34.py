def detect_anagrams(word, answerList):
    resultList = []
    sortedWord = ''.join(sorted(word.lower()))
    
    for x in range(len(answerList)):
        if sortedWord == ''.join(sorted(answerList[x].lower())):
            if not(word.lower() == answerList[x].lower()):                
                resultList.append(answerList[x])
                
    return resultList
