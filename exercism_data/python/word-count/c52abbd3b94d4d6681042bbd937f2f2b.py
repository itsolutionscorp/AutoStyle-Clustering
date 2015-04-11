def word_count(input_string):
    word_list=input_string.split()
    word_dict={}
    for word in word_list:
        if word not in word_dict:
            word_dict[word]=word_list.count(word)
    return word_dict



