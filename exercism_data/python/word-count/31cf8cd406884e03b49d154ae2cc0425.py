__author__ = 'junho'

def word_count(str):
    words = str.split()
    dic = {}
    for word in words:
        if word in dic:
            dic[word] += 1
        else:
            dic[word] = 1
    return dic
