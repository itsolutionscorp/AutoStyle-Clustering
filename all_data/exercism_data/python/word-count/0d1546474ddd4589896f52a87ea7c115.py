def word_count(phrase):
    """Function returns a dictionary counting occurances of words in an input string.

    note that no sanity checks are performed on input, it must be a string.
    """
    word_dict = {}
    for word in phrase.split():
        if word not in word_dict.keys():
            word_dict[word] = 1
        else:
            word_dict[word] += 1
    return word_dict
