def detect_anagrams(word, answerList):
    word = word.lower()
    sortedWord = sorted(word)
    
    return [x for x in answerList if sortedWord == sorted(x.lower())
            and word != x.lower() 
                  ]
