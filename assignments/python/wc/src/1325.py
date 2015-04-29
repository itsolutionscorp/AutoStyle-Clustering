def word_count(phrase):
    word_count_dict = {}
    for word in phrase.split():
        word_count_dict[word] = word_count_dict.get(word, 0) + 1
    return word_count_dict
