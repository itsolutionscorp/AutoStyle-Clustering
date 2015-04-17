def word_count(phrase):
    """
    (str) -> dict
    returns a dictionary listing the number of occurrences of each word
    in a phrase
    """
    d = {}
    for word in phrase.split():
        if word in d:
            d[word] += 1
        else:
            d[word] = 1
    return d
