from collections import Counter


def word_count(string):
    string_as_list = string.split()
    return dict(Counter(string_as_list))
