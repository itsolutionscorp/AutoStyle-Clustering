#-*- coding: utf-8 -*-
def word_count(my_sentence):
    my_sentence_as_list = my_sentence.split()
    my_dict = {}
    for my_word in my_sentence_as_list:
        if my_word not in my_dict:
            my_dict[my_word] = 1 
        else:
            my_dict[my_word] += 1
    return my_dict
if __name__ == '__main__':
    input = raw_input()
    print word_count(input)
