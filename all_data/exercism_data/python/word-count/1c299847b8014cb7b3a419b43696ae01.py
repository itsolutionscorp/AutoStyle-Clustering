"""The word-count excercise"""

def word_count(string):
    """Return a dictionary of words and their frequency"""

    mapping = {}

    for word in string.split():
        mapping[word] = mapping.setdefault(word, 0) + 1

    return mapping
