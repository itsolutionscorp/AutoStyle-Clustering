def word_count(sentence):
    wordCount = {}
    for word in sentence.split():
        if word not in wordCount:
            wordCount[word] = 1
        else:
            wordCount[word] += 1
    return wordCount