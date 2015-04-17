def word_count(phrase):
    frequency = {}
    for word in phrase.split():
        frequency[word] = frequency.get(word, 0) + 1
    return frequency
