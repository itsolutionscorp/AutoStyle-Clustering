def word_count(phrase):
    d = {}
    for word in phrase.split():
        d[word]=d.get(word, 0)+1
    return d
