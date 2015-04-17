from collections import Counter
def word_count(sentence):
    """Count the number of occurences of words within a string.
    Arg:
        sentence (string): the string of words.
    Returns:
        dict of word-count entries."""
    counted = Counter(sentence.split())
    return dict(counted)
