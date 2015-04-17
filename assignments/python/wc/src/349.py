def word_count(string):
    list = string.split()
    dict = {}
    for word in list:
        dict[word] = list.count(word)
    return dict
