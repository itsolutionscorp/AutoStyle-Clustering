from collections import defaultdict

def word_count(text):
    
    #dictionary that if queried for a nonexistent key
    #initializes said key with a value of 0
    res_dic = defaultdict(int)

    text = text.split()

    for word in text:
        res_dic[word] += 1

    return dict(res_dic)
