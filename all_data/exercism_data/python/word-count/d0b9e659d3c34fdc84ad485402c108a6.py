def word_count(phrase):
    frequency = {}
    for w in phrase.split():
        frequency[w] = frequency.get(w, 0) + 1
    return frequency
