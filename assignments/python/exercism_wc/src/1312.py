def word_count(sentence):
    words = sentence.split()
    d = {}
    for word in words:
        d[word] = d.get(word, 0) + 1
    return d
