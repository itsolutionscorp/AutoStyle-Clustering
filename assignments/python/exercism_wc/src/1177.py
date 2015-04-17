def word_count(sentence):
    word_list = sentence.split()
    word_dic = {}
    for word in word_list:
        word_dic[str(word)] = word_list.count(word)
    return word_dic
