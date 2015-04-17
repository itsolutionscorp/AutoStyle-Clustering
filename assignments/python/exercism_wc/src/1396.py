def word_count(sentence):
    wordcount = {}
    words = sentence.split()
    for eachWord in words:
        if eachWord in wordcount:
            wordcount[eachWord] = wordcount[eachWord] + 1
        else:
            wordcount[eachWord] = 1
    return wordcount
