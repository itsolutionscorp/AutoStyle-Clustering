def word_count(msg):
    msg = msg.split()
    dic = {}
    while len(msg) > 0:
        word = msg[0]
        count = msg.count(word)
        dic[word] = count
        for i in range(count):
            msg.remove(word)
    return dic
