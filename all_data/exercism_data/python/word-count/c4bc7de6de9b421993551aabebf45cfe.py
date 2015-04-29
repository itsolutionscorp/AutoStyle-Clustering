def word_count(sentence):
    res = {}
    for word in sentence.split():
        if not word in res:
            res[word] = 0
        res[word] += 1
    return res
        
