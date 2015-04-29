def word_count(phrase):
    words = phrase.split()  # Split the given phrase by whitespace

    counter = dict()

    for word in words:
        if word in counter.keys():  # Word seen before, increment counter
            counter[word] += 1
        else:  # First instance of word, add it to the dictionary
            counter[word] = 1

    return counter
