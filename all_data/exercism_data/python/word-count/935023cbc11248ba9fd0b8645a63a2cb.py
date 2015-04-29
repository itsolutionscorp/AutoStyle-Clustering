from collections import defaultdict

def word_count(word_string):
    word_array = word_string.split()
    histogram = defaultdict(lambda: 0)
    for word in word_array:
        histogram[word] += 1
    return dict(histogram)
