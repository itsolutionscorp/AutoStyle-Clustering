import re

def word_count(sents):
    words = {}
    for word in re.split(r"\s+", sents):
        if word in words:
            words[word] += 1
        else:
            words[word] = 1
    return words
