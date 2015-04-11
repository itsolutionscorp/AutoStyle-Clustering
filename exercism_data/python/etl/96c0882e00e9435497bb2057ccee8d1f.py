__author__ = 'tracyrohlin'

def transform(word_list):
    new_dict = {}
    for number, words in word_list.items():
        for word in words:
            new_dict[word.lower()] = number

    return new_dict
