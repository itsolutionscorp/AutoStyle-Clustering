def word_count(word):
    word_dict = {}
    word = word.strip()
    word_list = word.replace('\n', ' ').split(' ')
    for singe_word in word_list:
        word_dict[singe_word] = 0
    for singe_word in word_list:
        word_dict[singe_word] = word_dict[singe_word] + 1
    word_dict.pop('', None)
    return word_dict
