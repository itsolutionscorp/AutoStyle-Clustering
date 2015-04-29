import string

def word_count(words):

    # convert input to lowercase
    # strip punctuation
    # and convert it into an array
    words = words.translate(None, string.punctuation)\
        .split()

    # create a dict to hold the word counts
    # the word itself is the key, with the count being the value
    word_list = dict()

    # loop through the array of words.
    # If it already exists as a key, increment the value
    # otherwise add it as a new key
    for word in words:
        try:
            word_list[word.lower()] += 1
        except KeyError:
            word_list[word.lower()] = 1
    # return the final dict
    return word_list
