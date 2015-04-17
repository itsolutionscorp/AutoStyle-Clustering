def word_count(phrase):
    """ Returns every word in the given `phrase` and the number of times it occurs.
    :param phrase: String.
    :return: Dict mapping word to number of occurrences of word.
    """
    words = phrase.split(" ")
    word_counts = {}
    for word in words:
        if word not in word_counts.keys():
            word_counts[word] = 0
        word_counts[word] += 1
    return word_counts
