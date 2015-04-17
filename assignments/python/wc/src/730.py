import collections
def word_count(input):
    counter = collections.Counter()
    for word in input.split():
        counter[word] += 1
    return counter
