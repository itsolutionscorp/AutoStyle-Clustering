def word_count(phrase):
    words=phrase.split()
    word_dict = {}
    for w in words:
        if w in word_dict:
            word_dict[w]+=1
        else:
            word_dict.setdefault(w, 1)
    return word_dict
