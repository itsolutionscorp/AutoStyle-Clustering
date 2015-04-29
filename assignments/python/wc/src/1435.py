def word_count(input_string):
    word_list=input_string.split()
    word_dict={}
    for word in word_list:
        if word in word_dict:
            word_dict[word]+=1
        else:
            word_dict[word]=1
    return word_dict
