from collections import Counter

def word_count(phrase):
    """ Counting the occurence of each word in a string
    """

    # Split the phrase into individual items in a list, this is so we can iterate
    # over them and count them better
    phrase = phrase.split()

    # The 'Counter' is a native Python tool that can tally up the occurences of
    # items in a list
    return Counter(phrase)
