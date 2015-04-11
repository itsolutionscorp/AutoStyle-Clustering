def word_count(text):
    """Returns a dictionary containing counts of each word in text"""

    # Tokenize text on whitespace / newline
    words = text.strip().split()

    # Create a dictionary from the set of tokens, initializing each count to 0
    counts = dict.fromkeys(words, 0)

    # Iterate over the text to count occurences of each token
    for word in words:
        counts[word] += 1

    # Return the counts
    return counts
