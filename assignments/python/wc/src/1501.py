def word_count(sentence):
    list_of_words = sentence.split()
    wordcount = {}
    for word in list_of_words:
        if not word in wordcount:
            wordcount[word] = 1
        else:
            wordcount[word] += 1
    return wordcount
