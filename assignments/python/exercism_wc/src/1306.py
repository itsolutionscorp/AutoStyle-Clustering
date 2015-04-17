from collections import Counter
def word_count(phrase):
    '''
    Returns the numbers of times
    each word in a phrase occurs
    '''
    return Counter(phrase.split())
