def word_count(text):
    words = {}
    all_words = text.split()
    print all_words
    for w in all_words:
        if w in words:
            words[w] += 1
        else:
            words[w] = 1
    print words
    return words
