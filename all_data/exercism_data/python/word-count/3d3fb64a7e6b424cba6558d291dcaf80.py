def word_count(text):
    word_list = text.split()

    word_dict = {}
    for word in word_list:
        if len(word) != 0:
            if word in word_dict:
                word_dict[word] = word_dict[word] + 1
            else:
                word_dict[word] = 1

    return word_dict
