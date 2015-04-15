def word_count(sentence):
    """
    Returns a disctionary `words` which contains unique words in the given
    sentence and their counts.
    """
    words = {}
    for word in sentence.split():
        if word not in words:
            words[word] = 1
        else:
            words[word] += 1
    return words
