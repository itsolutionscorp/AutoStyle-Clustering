def word_count(phrase):
    phrase = phrase.split()
    occurences = {}
    for word in set(phrase):
        occurences[word] = phrase.count(word)
    return occurences
