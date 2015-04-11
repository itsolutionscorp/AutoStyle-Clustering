def word_count(sentence):
    word_list = {}

    for word in sentence.split():
        if word in word_list.keys():
            word_list[word] = word_list[word] + 1
        else:
            word_list[word] = 1

    return word_list
