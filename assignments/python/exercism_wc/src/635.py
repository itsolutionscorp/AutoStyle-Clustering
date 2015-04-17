def word_count (phrase):
    words = phrase.split ()
    dict = {}
    for word in words:
        if dict.has_key (word):
            dict [word] += 1
        else:
            dict [word] = 1
    return dict
