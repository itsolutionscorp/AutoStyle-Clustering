def word_count(sentence):

    w = {i:sentence.split().count(i) for i in sentence.split()}
    return w
