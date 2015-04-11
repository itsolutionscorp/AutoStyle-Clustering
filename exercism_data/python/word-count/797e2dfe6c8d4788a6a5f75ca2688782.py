import string

def word_count(input):

    # convert input to lowercase
    # strip punctuation
    # and convert it into an array
    words=input.translate(None, string.punctuation).lower().split()

    # create a dict to hold the word counts
    # the word itself is the key, with the count being the value
    list=dict()

    # loop through the array of words.
    # If it already exists as a key, increment the value
    # otherwise add it as a new key
    for word in words:
        if word in list:
            list[word] += 1
        else:
            list[word] = 1

    # return the final dict
    return list
