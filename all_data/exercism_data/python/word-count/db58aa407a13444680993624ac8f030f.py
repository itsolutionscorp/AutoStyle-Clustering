def word_count(phrase):
    counts = {}

    # Split the phrase up into a list containing each word
    for word in phrase.split():
        # If the word is in our dict, increment its count
        if word in counts:
            counts[word] += 1
        # If the word is NOT in our dict, add it with a count of one
        else:
            counts[word] = 1

    return counts
