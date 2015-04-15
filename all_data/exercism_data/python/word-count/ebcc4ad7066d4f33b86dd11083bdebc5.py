import re

def word_count(phrase):
    # Create result dictionary that we'll return
    result = {}
    # The simplest implementation is to just use split() to divide phrase
    words = phrase.split()
    # build a unique set for iteration (set eliminates dupes)
    wordSet = set(words)
    # For each element in the unique set, count occurrences in 'words' and 
    # enter the result into the result dictionary
    for w in wordSet:
        occurrence = 0
        for w2 in words:
            if w2 == w: # Basically count every non-unique occurrence of the unique list
                occurrence += 1
        result[w] = occurrence
    return result
