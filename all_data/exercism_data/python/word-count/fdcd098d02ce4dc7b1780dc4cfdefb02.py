__author__ = 'dmwoods'


def word_count(input_string):
    return {word: input_string.split().count(word) for word in input_string.split()}
