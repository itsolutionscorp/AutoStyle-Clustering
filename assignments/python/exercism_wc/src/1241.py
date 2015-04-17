import re
def word_count(input_string):
    word_list = input_string.split()
    output = dict.fromkeys(word_list , 0)
    for index in range(len(word_list)):
        output[word_list[index]] = output[word_list[index]] + 1
    return output
