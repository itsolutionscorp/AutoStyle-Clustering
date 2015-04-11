def word_count(sentence):
    chopped = sentence.split()
    word_dict ={}
    for word in chopped:
        word_dict[word] = chopped.count(word)
    return word_dict
