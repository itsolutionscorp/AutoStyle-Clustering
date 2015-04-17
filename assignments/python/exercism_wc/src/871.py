def word_count(str):
    word_list = {}
    for word in str.split():
        if word:
            word_list[word] = word_list.get(word, 0) + 1
    return word_list
