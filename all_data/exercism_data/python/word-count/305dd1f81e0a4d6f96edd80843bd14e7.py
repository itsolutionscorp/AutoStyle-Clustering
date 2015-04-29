def my_word_count(sentence):
    words = dict()
    for w in sentence.split():
        if w in words: words[w] += 1
        else: words[w] = 1
    return words
