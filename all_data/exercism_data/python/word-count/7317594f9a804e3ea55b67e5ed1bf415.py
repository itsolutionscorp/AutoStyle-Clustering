import re

def word_count(string):
    # Strip all non-alphanumeric characters from the lowercase string
    word_list = re.findall(r"\w+",string.lower())

    # Define the dictionary
    words = {}
    # For every word in the string:
    # If it is not already in the dictionary:
    # Add it to the dictionary as the key, and make the value
    # the number of times it appears in the string.
    for item in word_list:
        if not item in words:
            words[item] = word_list.count(item)

    return words
