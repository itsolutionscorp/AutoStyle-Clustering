import re

def word_count(sentence):
    #remove special character
    sentence = re.sub(r'[!&@$%?^.:,]', '', sentence)

    #1 space to separate words
    sentence = re.sub(r'\s+', ' ', sentence)

    #normalize case
    sentence = sentence.lower()

    #split sentence into words
    words = sentence.split(" ")

    counts = {}
    for word in words:
        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1

    return counts
