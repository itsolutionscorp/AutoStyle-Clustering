def word_count(my_sentence):
    my_dict = {}
    for my_word in my_sentence.split():
            my_dict[my_word] = my_dict.get(my_word, 0) + 1 
    return my_dict
if __name__ == '__main__':
    input = raw_input()
    print word_count(input)
