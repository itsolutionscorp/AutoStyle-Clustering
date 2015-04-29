from collections import Counter

def word_count(phrase):

    count = Counter(phrase.split())             #splits the phrase into elements

    return count								#returns element and element count
