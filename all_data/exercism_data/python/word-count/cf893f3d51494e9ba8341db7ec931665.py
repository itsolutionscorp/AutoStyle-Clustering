from collections import Counter

# Count the occurrences of each word in the string
def word_count(words):

    # Validate input
    if not isinstance(words,basestring):
        return None

    # Stores the count for every word
    word_counts = Counter()

    # Iterate through the list of words
    for word in words.split():
        word_counts[word] += 1

    return word_counts
