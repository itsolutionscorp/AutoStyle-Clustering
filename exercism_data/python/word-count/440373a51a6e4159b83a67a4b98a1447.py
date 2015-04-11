def word_count(phrase):
    ''' by default space is used to split'''
    list_word = phrase.split()
    res = {}
    for word in list_word:
        count = 0
        for word_2 in list_word:
            if word_2 == word :
                count += 1
        res[word] = count
    return res
