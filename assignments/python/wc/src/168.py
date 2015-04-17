def word_count(input_str):
    this_dict = {}
    split_str = input_str.split()
    for word in split_str:
        if word in this_dict:
            this_dict[word] += 1
        else:
            this_dict[word] = 1
    return this_dict
