def word_count(words):
    split_words = words.split()
    count_list = [split_words.count(word) for word in split_words]
    dict_return = dict(zip(split_words, count_list))
    return dict_return
