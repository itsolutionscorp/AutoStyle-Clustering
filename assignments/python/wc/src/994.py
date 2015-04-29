from collections import Counter
def word_count(phrase):
    """ Counting the occurence of each word in a string
    """
    phrase = phrase.split()
    return Counter(phrase)
