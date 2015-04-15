import re

def word_count(sentence):
    #normalize case
    sentence = sentence.lower()

    #remove non alpha-numerical characters
    sentence = re.sub(r'[^a-z0-9 ]', '', sentence)

    #split sentence into words
    words = sentence.split()

    counts = {}
    for word in words:
        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1

    return counts
