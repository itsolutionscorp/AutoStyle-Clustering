__author__ = 'agupt15'


def word_count(line):
    counts = dict()

    words = line.split()

    for word in words:
        if word in counts:
            counts[word]=counts[word] + 1
        else:
            counts[word]=1

    return counts
