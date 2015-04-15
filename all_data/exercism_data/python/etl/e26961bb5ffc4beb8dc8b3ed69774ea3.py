__author__ = 'emiller42'


def transform(old_scoring):
    new_score = {}
    for score in old_scoring:
        for letter in old_scoring[score]:
            new_score[letter.lower()] = score
    return new_score
