__author__ = 'Carl'

def word_count(phrase):
    words = str.split(phrase)
    counts = {}
    for word in words:
        if word in counts:
            counts[word]+=1
        else:
            counts[word]=1
    return counts
