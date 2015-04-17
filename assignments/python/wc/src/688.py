def word_count(word):
    word_list = word.split()
    temp = word_list[:]
    done=[]
    dic ={}
    for n in word_list:
        x = temp.count(n)
        if x==0:
            continue
        dic[n] = x
        for m in range(x):
            temp.remove(n)
    return dic
