__author__ = 'Blackfry'

def word_count(phrase):
    phrase_dict = {}
    words = phrase.split()

    for i in words:
        if i != "":
            if i not in phrase_dict:
                phrase_dict[i] = 1
            else:
                phrase_dict[i] += 1
    return phrase_dict
