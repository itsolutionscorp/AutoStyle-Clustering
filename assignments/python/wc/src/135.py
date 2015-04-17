def word_count(string):
    split_string_list = string.split()
    word_freq_dict = {}
    for word in split_string_list:
        if not word in word_freq_dict:
            word_freq_dict[word] = 1
        else:
            word_freq_dict[word] += 1
    return word_freq_dict
