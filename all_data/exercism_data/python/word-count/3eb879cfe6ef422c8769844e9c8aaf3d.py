def word_count(words):
    words_list = words.split()
    word_count = {}
    for word in words_list:
        if word and word not in word_count:
            word_count[word] = 1
        elif word:
            word_count[word] += 1
    return word_count
