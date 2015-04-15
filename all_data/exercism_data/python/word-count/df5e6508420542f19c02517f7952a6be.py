from collections import Counter
import string


def remove_punctuation(str_):
    return str_.translate(string.maketrans('', ''), string.punctuation)


def word_count(str_input):
    return Counter(word.lower() for word in
                   remove_punctuation(str_input).split())
