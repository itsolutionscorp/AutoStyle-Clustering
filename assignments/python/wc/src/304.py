def word_count(message):
    splitted=message.split()
    words=set([word for word in splitted])
    word_frequency=dict.fromkeys(words,0)
    for word in splitted:
        word_frequency[word]+=1
    return word_frequency
