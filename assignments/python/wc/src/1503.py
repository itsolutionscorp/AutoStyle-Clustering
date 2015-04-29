def word_count(sentence):
    words_freq = {}
    whitespace = ' \n\t'
    sentence = sentence.replace('\n', ' ') # then could split with white space
    words = sentence.split(' ')
    for word in words:
        if word in whitespace:
            continue
        if not word in words_freq:  # new word
            words_freq[word] = 1
        else:
            words_freq[word] += 1
    return words_freq
