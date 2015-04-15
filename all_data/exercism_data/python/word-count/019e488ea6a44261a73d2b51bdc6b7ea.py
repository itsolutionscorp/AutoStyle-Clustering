import re


def word_count(word):
    test = re.split(' |\n', word)
    test = list(filter(''.__ne__, test))
    words = {}

    while test:
        current = test[0]
        count = test.count(current)
        words[current] = count
        test = list(filter(current.__ne__, test))

    return words
