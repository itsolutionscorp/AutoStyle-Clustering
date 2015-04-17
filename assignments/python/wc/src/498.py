def word_count(sentence):
    d = dict()
    words = sentence.split()
    for word in words:
        d[word] = d.get(word,0) + 1
    return d
