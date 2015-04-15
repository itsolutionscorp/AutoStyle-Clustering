def distance(word, word2):
    res = 0
    for i, char in enumerate(word):
        if word2[i] != char:
            res += 1
    return res
