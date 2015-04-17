def word_count(text):
    """
    Returns a dictionary recording the occurance of each unique word in 'text'.
    For each key-value pair, the key is the word in question, and the value is
    the number of occurances of that word in 'text'.
    """
    word_count = {}
    for word in text.split():
        if word in word_count:
            word_count[word] += 1
        else:
            word_count[word] = 1
    return word_count
