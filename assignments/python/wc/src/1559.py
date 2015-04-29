def word_count(my_sentence):
    my_sentence_as_list = my_sentence.split()
    my_sentence_as_tuple = tuple(my_sentence_as_list)
    my_dict = {}
    for i in range(0, len(my_sentence_as_list)):
        my_word = my_sentence_as_list.pop(0)
        if my_word not in my_dict:
            my_dict[my_word] = my_sentence_as_tuple.count(my_word) 
    return my_dict
if __name__ == '__main__':
    input = raw_input()
    print word_count(input)
