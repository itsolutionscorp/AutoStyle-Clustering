import collections as c
def word_count(phrase):
    phrase = phrase.split()
    count = c.Counter()
    for word in phrase:
        count[word] += 1
    return count
