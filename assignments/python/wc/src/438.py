def word_count(sents):
    words = {}
    for word in sents.split():
        words[word] = words.get(word, 0) + 1
    return words
