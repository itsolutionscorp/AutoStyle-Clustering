def word_count(words):
    '''input a string and return a dictionary with a count of each distinct word'''

    word_list = words.split()
    word_dict = {}
    for word in word_list:
        if word in word_dict:
            word_dict[word] += 1
        else:
            word_dict[word] = 1
    return word_dict
