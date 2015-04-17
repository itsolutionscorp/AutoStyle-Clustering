def word_count(words):
    words = words.split()
    dic = {}
    for word in words:
        if word in dic:
            dic[word] += 1
        else:
            dic[word] = 1
    return dic
