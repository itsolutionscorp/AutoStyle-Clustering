import string

def word_count(input):
    """
    input: Text to parse
    returns frequency of individual words
    """

    frequencies = {}

    for word in input.split(' '):
        word = string.join([x for x in word if x.isalpha() or x.isdigit()],'').lower()
        if (len(word) == 0): #'word' was all non-alphanumeric characters
            continue
        if (frequencies.has_key(word)):
            frequencies[word] += 1
        else:
            frequencies[word] = 1

    return frequencies
