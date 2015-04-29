def word_count(input_string):
    word_count = {}
    for word in input_string.split():
        if word in word_count.keys():
            word_count[word] += 1
        else:
            word_count[word] = 1

    return word_count
