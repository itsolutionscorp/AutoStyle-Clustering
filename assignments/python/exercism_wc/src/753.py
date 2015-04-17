def word_count(in_string):
    words = in_string.split()
    word_set = set(words)
    out = {}
    for i in list(word_set):
        n_word = 0
        for j in words:
            if i == j: n_word += 1
        out.update({i: n_word})
    return out
