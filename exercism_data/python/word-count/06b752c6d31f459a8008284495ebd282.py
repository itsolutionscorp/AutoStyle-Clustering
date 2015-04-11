def word_count(phrase):
    ''' counts the number of occurrences of each word in a given phrase
    '''

    words = phrase.split()
    word_dict = {}
    for word in words:
        word_dict[word] = word_dict.get(word, 0) + 1
    return word_dict
