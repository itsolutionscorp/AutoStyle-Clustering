def word_count(sentence):
    words = {}
    for word in sentence.split():
        if word not in words:
            words[word] = 1
        else:
            words[word] += 1
    return words
