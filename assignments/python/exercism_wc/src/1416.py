def word_count(text):
    wordcount = {}
    words = text.split()
    for word in words:
        if word not in wordcount:
            wordcount[word] = 1
        else:
            wordcount[word] += 1
    return wordcount
