import string

def word_count(phrase):
    # Remove punctuation from the phrase and split phrase in to the list of words
    for c in string.punctuation:
        phrase.replace(c, '')
    words = phrase.split()

    # Count each occurrence of the each word in the list and add it to a dict
    # If word is not in the dict it's its first occurrence, else it's the next occurrence
    counter = {}
    for word in words:
        if word not in counter:
            counter[word] = 1
        else:
            counter[word] += 1

    return counter
