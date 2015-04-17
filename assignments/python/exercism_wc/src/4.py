def word_count(arg):
    sentence = str(arg).lower().split()
    word_list = {}
    for word in sentence:
        word = word.strip( '!@#$%^&*,:;<>?' )
        if word == '':
            pass
        elif word not in word_list: 
            word_list[word] = 1
        else:
            word_list[word] += 1
    return word_list
