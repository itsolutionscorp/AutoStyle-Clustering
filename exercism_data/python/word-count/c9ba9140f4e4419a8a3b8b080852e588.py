from collections import defaultdict
def word_count(phrase):

    mycounter = defaultdict(lambda: 0)
    for word in phrase.split():
        mycounter[word] += 1

    return mycounter
