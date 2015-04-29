def word_count(phrase):
    """
    Count each word in the given phrase.
    """
    wordcount = {}
    words = phrase.split()
    for word in words:
        if word in wordcount:
            wordcount[word] += 1
        else:
            wordcount[word] = 1
    return wordcount
