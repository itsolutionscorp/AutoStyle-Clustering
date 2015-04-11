"""
wordcount.py: Return a dict with words and counts in a given string.
"""


def word_count(words):
    word_lst = words.split()
    word_dict = {}
    for word in word_lst:
        if word in word_dict:
            word_dict[word] += 1
        else:
            word_dict[word] = 1
    return word_dict
