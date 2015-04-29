from collections import Counter
def word_count(phrase):
    """ count occurances of each word in the phrase
        and total them up. """
    return dict(Counter(phrase.split()))
