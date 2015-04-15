def word_count(phrase):

    word_list = {}
    words = phrase.split()

    for word in words:
        if word in word_list:
            word_list[word] = word_list[word] + 1
        else:
            word_list[word] = 1

    return word_list
