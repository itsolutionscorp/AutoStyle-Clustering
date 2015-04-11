__author__ = 'emiller42'


def transform(old_scoring):
    return {letter.lower(): score for score in old_scoring for letter in old_scoring[score]}
