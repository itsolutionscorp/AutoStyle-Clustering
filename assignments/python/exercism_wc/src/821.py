"""
wordcount - a module for counting word in phrases.
"""
def word_count(phrase):
    """
    Count the number of occurrences of words in a given phrase.
    """
    word_counts = {}
    for word in phrase.split():
        if word in word_counts:
            word_counts[word] += 1
        else:
            word_counts.update({word: 1})
    return word_counts
