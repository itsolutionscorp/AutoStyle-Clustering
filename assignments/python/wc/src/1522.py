def word_count(string):
    dict_ = dict()
    for word in string.split():
        if word in dict_:
            dict_[word] += 1
        else:
            dict_[word] = 1
    return dict_
