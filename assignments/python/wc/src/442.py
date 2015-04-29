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
    words = {}
    text = ' '.join(text.split())
    for word in text.split(" "):
        if word in words:
            words[word] += 1
        else:
            words[word] = 1
    return words
