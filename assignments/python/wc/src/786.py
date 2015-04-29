import re
import collections
def word_count(string):
    word_list = re.split(r'\s+', string)
    word_counts = collections.Counter(word_list)
    return word_counts
