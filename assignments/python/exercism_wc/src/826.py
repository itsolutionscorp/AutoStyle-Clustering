def word_count(sentence):
    sentence = sentence.split()
    wordHash = {}
    for word in sentence:
        if word in wordHash:
            wordHash[word] = wordHash[word] + 1
        else:
            wordHash[word] = 1
    return wordHash
