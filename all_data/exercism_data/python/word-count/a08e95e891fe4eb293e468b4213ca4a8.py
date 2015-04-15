def word_count(string):
    l = string.split()
    res = {}
    for phrase in l:
        if phrase not in res.keys():
            res[phrase] = 1
        else:
            res[phrase] += 1
    return res
