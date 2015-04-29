def word_count(text):
    word_list = text.split()
    word_count = {}
    for word in set(word_list):
        n = 0
        for count_word in word_list:
            if count_word == word:
                n += 1
        word_count[word] = n
    return word_count
