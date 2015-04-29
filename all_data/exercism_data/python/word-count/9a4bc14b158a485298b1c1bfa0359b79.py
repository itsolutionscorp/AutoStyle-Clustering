from collections import defaultdict

def word_count(words):
    words_list = words.split()
    word_count = defaultdict(int)
    for word in words_list:
        word_count[word] += 1
    return word_count
