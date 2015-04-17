def word_count(phrase):
    occurences = {}
    for word in phrase.split():
        occurences[word] = occurences.get(word, 0) + 1
    return occurences
