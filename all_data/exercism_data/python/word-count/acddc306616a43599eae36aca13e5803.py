from collections import defaultdict

def word_count(text):
    words = text.split()
    word_count_dict = defaultdict(int)
    for word in words:
        word_count_dict[word] += 1
    return word_count_dict
