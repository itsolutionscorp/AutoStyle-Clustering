# word counting function

def word_count(text):
    """
    given a string, returns a dictionary.
    keys are the words in the text,
    values are counts of these words.
    """
    # first separate with whitespace and get one copy of each word
    allwords = text.split()  # a list of words
    words = set(allwords)  # that is a set, therefore we avoid duplicates.

    # make a dict comprehension from this using list.count()
    # and by the way, return it.
    return {word:allwords.count(word) for word in words}
