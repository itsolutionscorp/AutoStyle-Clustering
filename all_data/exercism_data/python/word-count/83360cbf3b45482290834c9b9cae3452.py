def word_count(phrase):
    """
    Breaks phrase up into individual words. Stores these individual words as the
    keys of a dictionary. The values in the dictionary are the number of times
    a particular word occurred in the phrase.
    """
    words = phrase.split()
    counts = {}
    for word in words:
        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1

    return counts
