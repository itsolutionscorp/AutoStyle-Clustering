def word_count(sentence):
    
    a = dict()
    words_list = sentence.split()
    for i in words_list:
        if i in a:
            a[i] += 1
        else:
            a[i] = 1

    return a
