def word_count(input_words):
    input_words = input_words.lower()
    words_list = input_words.split()
    print(words_list)
    for word in words_list:
        print "{0}: {1}".format(word, words_list.count(word))
        if words_list.count(word) > 1:
            words_list.remove(word)
