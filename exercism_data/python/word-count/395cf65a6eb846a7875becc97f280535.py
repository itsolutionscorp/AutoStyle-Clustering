import re


def word_count(phrase):
    tally = {}
    words = re.split("[ \n]+", phrase)
    for word in words:
        if word in tally:
            tally[word] += 1
        else:
            tally[word] = 1
    return tally
