def word_count(sentence):
    word_list = sentence.split()
    word_dict = {}
    i = 0
    while i < len(word_list):
        if word_list[i] not in word_dict:
            word_dict[word_list[i]] = 1
        else:
            word_dict[word_list[i]] += 1
        i += 1
    return word_dict
