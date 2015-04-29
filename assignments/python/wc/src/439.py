from collections import Counter
def word_count(phrase):
    """ Returns the number of words in 'phrase' """
    words = Counter(phrase.replace('\n', ' ').split(' '))
    if '' in words.keys():
        del words['']
    return words
