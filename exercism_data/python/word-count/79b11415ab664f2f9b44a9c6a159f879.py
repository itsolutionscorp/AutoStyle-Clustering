def word_count(text):
    myList = text.split()
    uniqWords = sorted(set(myList))
    myDict={}
    for word in uniqWords:
        #print(word, myList.count(word))
        myDict.update({word : myList.count(word)})
    return myDict
