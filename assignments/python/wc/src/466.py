from collections import Counter
def word_count(phrase):
    ''' counts the number of occurrences of each word in a given phrase
    '''
    return Counter(phrase.split())
