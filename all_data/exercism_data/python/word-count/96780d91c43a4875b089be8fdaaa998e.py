from collections import defaultdict


def word_count(phrase):
    word_count_dict = defaultdict(int)
    phrase = phrase.replace("\n", " ")
    
    for word in phrase.split():
        word_count_dict[word] += 1
    return word_count_dict
