def word_count(phrase):
    occurences = {}
    for word in phrase.split():
        try:
            occurences[word] += 1
        except KeyError:
            occurences[word] = 1
    return occurences
