def word_count(sentence):
    words_freq = {}
    words = sentence.split()
    for word in words:
        if not word in words_freq:  # new word
            words_freq[word] = 1
        else:
            words_freq[word] += 1
    return words_freq
