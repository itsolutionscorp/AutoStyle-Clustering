def word_count(phrase):
    """Returns the count of each word in a given phrase."""
    occurances = {}
    for word in phrase.split():
        if word in occurances.keys():
            occurances[word] += 1
        else:
            occurances[word] = 1
    return occurances
