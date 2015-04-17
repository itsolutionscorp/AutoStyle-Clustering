'''This module takes a string input and outputs the word count for each word'''
def word_count(phrase):
    words = phrase.split()
    return_dict = {}
    for word in words:
        if word in return_dict:
            return_dict[word] = return_dict[word] + 1
        else:
            return_dict[word] = 1
    return return_dict