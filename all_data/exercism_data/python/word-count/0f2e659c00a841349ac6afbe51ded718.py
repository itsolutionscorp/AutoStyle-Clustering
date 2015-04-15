#
# Determines word count in a given phrase
#


def word_count(phrase):
    """
    Splits string and sequentially adds it to word_dict if it has not already occurred
    if it has, the corresponding number is incremented by one
    """
    word_list = phrase.split(" ")
    word_dict = {}
    for word in word_list:
        if word not in word_dict:
            word_dict[word] = 1
        elif not word == '':
            word_dict[word] += 1
    return word_dict
