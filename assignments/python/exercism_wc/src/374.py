"""
Word Count
"""
def word_count(phrase):
    word_count_dict = {}
    word_list = phrase.split()
    for word in word_list:
        if word in word_count_dict:
            word_count_dict[word] += 1
        else:
            word_count_dict[word] = 1
    return word_count_dict
