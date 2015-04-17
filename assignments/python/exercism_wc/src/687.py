"""
Implementation of wordcount
Specification:
    split a string into words with whitespace as the separator
    count the number of occurences of each word
"""
def word_count(phrase):
    """ return a dictionary of word:count pairs """
    w_counts = {}
    for word in phrase.split():
        w_counts[word] = w_counts.get(word, 0) + 1
    return w_counts
