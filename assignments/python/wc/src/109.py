def word_count(sentence):
    outputList = {}
    for w in sentence.split():
        if w in outputList:
            outputList[w] += 1
        else:
            outputList[w] = 1
    return(outputList)
