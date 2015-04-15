"""
count the occurrences of each word in that phrase
"""


def word_count(phase):
    wd = {}
    words = phase.strip().split()
    for w in words:
        if w in wd.keys():
            wd[w] += 1
        else:
            wd[w] = 1
    return wd
