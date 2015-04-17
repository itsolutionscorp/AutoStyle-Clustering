import re
def word_count(string):
    count_dict = {}
    for s in filter(None, re.split('\W+', string.lower())):
        if s not in count_dict:
            count_dict[s] = 1
        else:
            count_dict[s] += 1
    return count_dict
