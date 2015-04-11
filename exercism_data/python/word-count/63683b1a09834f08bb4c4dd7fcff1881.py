def word_count(text):
    words = text.split()
    d = {x:words.count(x) for x in words}
    return d
