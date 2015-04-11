# Takes a string, counts the number of occurrences of each word,
# and returns as a list.

import re


def word_count(string):
    # Split the string at the nonword characters
    word_list = re.split(r'\s+', string)

    # Create a dictionary to store the word counts
    word_counts = {}

    # Iterate through the list of words and increment word counts
    for word in word_list:
        if word in word_counts:
            word_counts[word] += 1
        else:
            word_counts[word] = 1

    # Return the dictionary
    return word_counts
