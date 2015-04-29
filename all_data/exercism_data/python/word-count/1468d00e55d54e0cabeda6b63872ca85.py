from collections import defaultdict

def word_count(sentence):
    count_dict = defaultdict(int)
    words = sentence.split()
    for word in words:
        count_dict[word] += 1
    return count_dict
