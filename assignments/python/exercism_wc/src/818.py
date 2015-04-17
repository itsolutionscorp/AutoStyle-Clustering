def word_count(text):
    words = text.split()
    return dict([(x, words.count(x)) for x in words])
