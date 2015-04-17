def word_count(words):
    split = words.split()
    dict = {}
    for word in split:
        try:
            dict[word] += 1
        except KeyError:
            dict[word] = 1
    return dict
