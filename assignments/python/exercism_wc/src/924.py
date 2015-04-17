def word_count(text):
    """
    given a string, returns a dictionary.
    keys are the words in the text,
    values are counts of these words.
    """
    allwords = text.split()  # a list of words
    words = set(allwords)  # that is a set, therefore we avoid duplicates.
    return {word:allwords.count(word) for word in words}
