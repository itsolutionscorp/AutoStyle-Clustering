# wordcount.py
# exercism.io: Python Exercise #3
from collections import defaultdict


def word_count(phrase):
    """Returns defaultdict with the num of occurances of each word in phrase"""

    # use a defaultdict to make it easier to add new words to the dict
    count = defaultdict(int)

    # split the phrase (at whitespace) into an array of words
    words = phrase.split()

    # iterate through the list of words and increment the count of each word
    for word in words:
        count[word] += 1

    return count
