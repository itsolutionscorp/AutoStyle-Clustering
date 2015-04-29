from collections import defaultdict

def word_count(input_str):
    wordcount_dict = defaultdict(int)
    split_str = input_str.split()
    for word in split_str:
        wordcount_dict[word] += 1
    return wordcount_dict
