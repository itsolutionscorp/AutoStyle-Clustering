def word_count(message):
    word_dict = {}
    messagelist = message.split()
    for word in messagelist:
       word_dict.setdefault(word, 0)
       word_dict[word] += 1
    return word_dict
