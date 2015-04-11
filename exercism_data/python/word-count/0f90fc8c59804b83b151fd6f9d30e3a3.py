from collections import Counter

def word_count(phrase):

    count = Counter(phrase.split())             #splits the phrase into elements

    return {key: count[key] for key in count}   #returns element and element count
