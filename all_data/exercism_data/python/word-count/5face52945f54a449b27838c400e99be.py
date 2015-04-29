from collections import Counter
from string import ascii_letters, digits


def word_count(string):
    cleaned_word_list = [
        remove_nonalpha(word)
        for word in string.lower().split(' ')
        if remove_nonalpha(word)
    ]
    return Counter(cleaned_word_list)


def remove_nonalpha(word):
    clean = ''.join([c for c in word if c in (ascii_letters + digits)])
    return clean
