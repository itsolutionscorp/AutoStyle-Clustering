import collections

def word_count(text):
    count_dict = collections.defaultdict(int)
    for word in text.split():
        count_dict[word] += 1

    return count_dict
