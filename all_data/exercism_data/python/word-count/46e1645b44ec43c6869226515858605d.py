import re
from collections import Counter


def word_count(sentence):
    sentence = re.sub('[^a-z\d\s]', '', sentence.lower())
    count_dict = Counter(sentence.split())
    return count_dict
