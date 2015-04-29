def word_count(phrase):
    words = {}
    phrase = phrase.split()
    for w in phrase:
        words[w] = phrase.count(w)
    return words
