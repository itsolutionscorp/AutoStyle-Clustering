def word_count(text):
    words = text.split()
    counted_words = {}
    for word in words:
        i = 0
        if word in counted_words.keys():
            continue
        else:
            for test_word in words:
                if word == test_word:
                    i += 1
            counted_words[word] = i
    return counted_words
