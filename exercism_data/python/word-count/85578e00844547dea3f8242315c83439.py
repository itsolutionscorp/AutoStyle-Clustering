# Takes a string, counts the number of occurrences of each word,
# and returns as a list.

import re
import collections


def word_count(string):
    # Split the string at the whitespace characters
    word_list = re.split(r'\s+', string)

    # Create a dictionary to store the word counts
    word_counts = collections.Counter(word_list)

    # Return the dictionary
    return word_counts
