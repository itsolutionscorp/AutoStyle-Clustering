__author__ = 'Carl'

def word_count(phrase):
    words = str.split(phrase)
    counts = {}
    if len(words) == 1:
        return {words[0]:1}
    for word in words:
        if word in counts:
            counts[word]+=1
        else:
            counts[word]=1
    return counts
