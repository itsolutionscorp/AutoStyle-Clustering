def word_count(words):
    word_dict = {}
    for w in words.split():
        word_dict[w] = word_dict.get(w, 0) + 1
    return word_dict
