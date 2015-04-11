def word_count(phrase):
    word_list = phrase.split()
    word_dict = {}

    for i in word_list:
        if word_dict.has_key(i):
            word_dict[i] += 1
        else:
            word_dict[i] = 1

    return word_dict
