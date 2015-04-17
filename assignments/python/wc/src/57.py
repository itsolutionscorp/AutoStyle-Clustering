def word_count(words):
    list_of_words = words.split()
    dict_of_word_frequencies = {}
    for w in list_of_words:
        if w in dict_of_word_frequencies:
            dict_of_word_frequencies[w] += 1
        else:
            dict_of_word_frequencies[w] = 1
    return dict_of_word_frequencies
