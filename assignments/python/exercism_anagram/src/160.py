def detect_anagrams(givenWord, wordList):
    anagrams=[]
    for word in wordList:        
        if sorted(givenWord.lower())==sorted(word.lower()) and (givenWord.lower() !=word.lower()):
            anagrams.append(word)
    return anagrams
