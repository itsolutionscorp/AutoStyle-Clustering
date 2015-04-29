def word_count(phrase):
    # empty dictionary to store the results
    word_counts = {}

    for word in phrase.split():
        # add 1 to the value of the key
        # if key doesn't exist, create it with value of 1
        word_counts[word] = word_counts.get(word, 0) + 1
    
    return word_counts
