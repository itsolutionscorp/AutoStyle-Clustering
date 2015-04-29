def word_count(text):
    """
    :type text: str
    :rtype : dict [str, int]
    """
    counts = {}
    for word in text.split():
        word = word.strip()
        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1
    return counts
