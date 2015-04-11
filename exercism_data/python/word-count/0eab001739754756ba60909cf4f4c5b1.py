def word_count(string):

    count_dict = {}
    for word in string.split():
        count_dict[word] = count_dict.get(word, 0) + 1

    return count_dict
