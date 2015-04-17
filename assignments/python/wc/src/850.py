def word_count(sentence):
    occurences = {}
    for word in sentence.split():
        if occurences.get(word, None):
            occurences[word] += 1
        else:
            occurences[word] = 1
    return occurences
