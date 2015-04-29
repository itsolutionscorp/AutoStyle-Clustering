"""
wordcount


"""


def word_count(text):
    """
    Return a dictionary of words and their
    word counds

    :param text:
    :return:
    """
    # Use a dictionary to store the words
    words = {}

    # Simple way to strip extra whitespace
    text = ' '.join(text.split())

    # Now iterate through, splitting on space
    for word in text.split(" "):
        if word in words:
            words[word] += 1
        else:
            words[word] = 1

    return words
