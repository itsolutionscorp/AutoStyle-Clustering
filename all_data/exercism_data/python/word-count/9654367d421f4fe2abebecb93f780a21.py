from collections import defaultdict


def get_words(para):
    words = []
    lines = para.split('\n')
    for line in lines:
        words += line.split(' ')
    return words


def word_count(input_string):
    words = get_words(input_string)
    word_dict = defaultdict(int)

    for word in words:
        if not word:
            continue
        word_dict[word] += 1

    return word_dict
