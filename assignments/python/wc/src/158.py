def word_count(words):
    return_dictionary = {}
    words_array = words.split();
    for word in words_array:
        if word in return_dictionary.keys():
            return_dictionary[word] += 1
        else:
            return_dictionary[word] = 1
    return return_dictionary
