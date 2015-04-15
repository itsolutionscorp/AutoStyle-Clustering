def word_count(sentence):
    """Returns a dictionary with the count of words in a string"""
    bag = sentence.split()
    bag = clean(bag)
    return count(bag)


def clean(words):
    """ Removes words of whitespace from the list words"""
    return [word.strip() for word in words]


def count(words):
    """ Returns a dictionary of word counts in the list words"""
    word_count = {}
    for word in words:
        if word in word_count:
            word_count[word] += 1
        else:
            word_count[word] = 1
    return word_count
