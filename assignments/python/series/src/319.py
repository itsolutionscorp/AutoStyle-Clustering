"""
This module has one function, slices(x, y), that takes a string x, and
generates a list of possible substrings of length y, in the form
of a list of characters.
"""
def slices(string, length):
    """
    Takes a string and a length. Generates as many substrings as possible
    of that length and presents a list of them as a list of characters.
    """
    if not length or length > len(string):
        raise ValueError
    list_of_subs = list()
    for i in range(len(string)):
        if i + length <= len(string):
            sub = list()
            for j in range(i, i + length):
                sub.append(int(string[j]))
            list_of_subs.append(sub)
    return list_of_subs
