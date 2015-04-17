def word_count(sentence):
    new = sentence.split()
    d_list = {}
    for word in new:
        d_list[word] = new.count(word)
    return d_list
