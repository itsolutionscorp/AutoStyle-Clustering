import re
from collections import Counter


def word_count(sentence):
    # convert to all lower
    sentence = sentence.lower()
    # trim everything but lower, digits, spaces
    sentence = re.sub('[^a-z\d\s]', '', sentence)
    # break into words for a list
    word_list = sentence.split()
    # count word occurences in list, save in dictionary
    count_dict = Counter(word_list)
    return count_dict
