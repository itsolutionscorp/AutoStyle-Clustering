def word_count(word):
    word_list = word.split()
    word_dict = {}
    for x in word_list:
        if x not in word_dict:
            word_dict[x] = 1
        else:
            word_dict[x] += 1
    return word_dict
