def word_count(phrase):
    """ Counting the occurence of each word in a string
    """
    phrase = phrase.split(" ")
    newphrase = {}
    for item in phrase:
        if item in newphrase:
            newphrase[item] += 1
        else:
            newphrase[item] = 1
    return newphrase
