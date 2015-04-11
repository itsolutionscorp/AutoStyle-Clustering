from re import split
from collections import Counter


def word_count(phrase):
    #count up the instances of each word in a Counter object
    counter = Counter()
    
    #let the regular expression split() do all the work 
    #'\W+' splits on any non-word character
    for word in split('\W+', phrase.lower()):
    
        #skip over empty strings
        if word == '' : continue
    
        #increment the counter for a given word
        counter[word] += 1

    return counter
