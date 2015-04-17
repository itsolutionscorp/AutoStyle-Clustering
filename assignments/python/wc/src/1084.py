def word_count(phrase):
    word_dict = {}
    for word in phrase.split():
        try:
            word_dict[word] += 1
        except:
            word_dict[word] = 1
    return word_dict
