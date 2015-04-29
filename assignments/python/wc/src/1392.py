def word_count(sentence):
    x = 0
    y = 0
    dict1 = {}
    sentence = sentence.split()
    for y in range(len(sentence)):
        x = 0
        for word in sentence:
            if(word == sentence[y]):
                dict1[word] = x +1
                x = x + 1
        y = y + 1
    print dict1
    return dict1
