def word_count(input):
    words = {}
    input_words = input.split()
    for thisword in input_words:
        if thisword not in words:
            words[thisword] = 1
        else:
            words[thisword] += 1
    return words
