__author__ = 'shandr'
def word_count(text):
    words_dict = {}
    words_list = text.split()
    for word in words_list:
        if word in words_dict:
            words_dict[word] +=1
        else:
            words_dict[word] = 1
    return words_dict
