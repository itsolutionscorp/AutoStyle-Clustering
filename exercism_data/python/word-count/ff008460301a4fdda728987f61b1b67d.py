def word_count(phrase):
    word_list = phrase.split()
    word_dict = {}

    for i in range(len(word_list)):
        if word_list[i] not in word_dict:
            word_dict[word_list[i]] = 1
        else:
            word_dict[word_list[i]] += 1

    return word_dict
