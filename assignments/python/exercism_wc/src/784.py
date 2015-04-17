import re
def word_count(string):
    word_list = re.split(r'\s+', string)
    word_counts = {}
    for word in word_list:
        if word in word_counts:
            word_counts[word] += 1
        else:
            word_counts[word] = 1
    return word_counts
