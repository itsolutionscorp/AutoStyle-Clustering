def word_count(phrase):
    words = {}
    for w in phrase.split():
        words[w] = words.get(w, 0) + 1
    return words
