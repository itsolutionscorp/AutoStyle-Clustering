def word_count(phrase):
    word_list = phrase.split()
    word_dict = {}
    for word in word_list:
        try:
            word_dict[word] += 1
        except:
            word_dict[word] = 1
    return word_dict
