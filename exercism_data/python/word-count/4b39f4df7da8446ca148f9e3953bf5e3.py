def word_count(text):
    words = text.split()
    d = {}
    for word in words:
        d[word] = words.count(word)
    return d
