def word_count(string):
    # split the string into individual words
    words = string.split()
    # create dictionary to store counts in
    word_counts = {}

    # iterate through the words
    for word in words:
        # for each word, get its previous count from the dictionary (or 0 if it hasn't been seen)
        # then increment the count by one and put it back in teh dictionary
        word_counts[word] = word_counts.get(word, 0) + 1

    return word_counts
