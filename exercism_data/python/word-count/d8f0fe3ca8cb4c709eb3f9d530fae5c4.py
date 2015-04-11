# Count the occurrences of each word in the string
def word_count(words):

    # Return None if the input is None or if it is not a string
    if words is None or not isinstance(words,basestring):
        return None

    # Stores the count for every word
    word_counts = dict()

    # Split the string into a list of words
    word_list = words.split()

    # Iterate through the list of words
    for word in word_list:

        # Just add 1 to the counter if the key is already present
        if word in word_counts:
            word_counts[word] += 1

        # Otherwise create the new key
        else:
            word_counts[word] = 1

    return word_counts
