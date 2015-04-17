def word_count(words):
    word_list = words.split()
    word_counter = {}
    for word in word_list:
        if word in word_counter:
            word_counter[word] += 1
        else:
            word_counter[word] = 1
    return word_counter
