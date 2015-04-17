import re
def word_count(phrase):
    result = {}
    words = phrase.split()
    wordSet = set(words)
    for w in wordSet:
        occurrence = 0
        for w2 in words:
            if w2 == w: # Basically count every non-unique occurrence of the unique list
                occurrence += 1
        result[w] = occurrence
    return result
