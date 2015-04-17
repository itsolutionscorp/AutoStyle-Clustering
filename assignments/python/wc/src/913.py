def word_count(words):
    """
    Counts the occurrences of each word in a string and returns a dict with 'word': count pairs
    """
    list_words = words.split()  # splits on whitespace
    word_counts = {word: list_words.count(word) for word in list_words}  # yay dict comprehensions!
    return word_counts
