def word_count(input_string):
    '''takes a string returns a dict in form 
    word: number of occurences of the word in the input string'''
    words_dict = {}
    list_of_words = input_string.split()
    for word in list_of_words:
        if word in words_dict:
            words_dict[word] += 1
        else:
            words_dict[word] = 1
        return words_dict
