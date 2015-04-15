from collections import defaultdict

def word_count(input_string):
    word_count = defaultdict(int)
    for word in input_string.split():
        word_count[word] += 1

    return word_count
