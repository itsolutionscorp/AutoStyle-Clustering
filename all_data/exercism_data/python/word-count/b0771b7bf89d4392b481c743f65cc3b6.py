def word_count(phrase):
    
    # Set of unique words
    word_set = set(phrase.split())

    # Empty dict to hold word counts
    w_counts = {}

    # Init to 0. Got to be a more Pythonic way
    for w in word_set:
        w_counts[w] = 0

    # Increment counter for each occurence.
    for w in phrase.split():
        if w in word_set:
            w_counts[w] += 1

    return w_counts
