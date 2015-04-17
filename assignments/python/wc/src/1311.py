def word_count(text):
    word_dict = {}
    for word in text.split():
        try:
            word_dict[word] += 1
        except:
            word_dict[word] = 1
    return word_dict
