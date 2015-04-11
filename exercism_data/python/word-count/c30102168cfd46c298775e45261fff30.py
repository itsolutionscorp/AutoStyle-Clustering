from collections import defaultdict

def word_count(input_str):
    wordcount_dict = defaultdict(int)
    for word in input_str.split():
        wordcount_dict[word] += 1
    return wordcount_dict
