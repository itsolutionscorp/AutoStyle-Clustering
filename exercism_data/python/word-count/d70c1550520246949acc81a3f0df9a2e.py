def word_count(text):
    ''' count number of occurence in text '''
    words = {}
    for token in text.split():
        if token in words:
            words[token] += 1
        else:
            words[token] = 1
    return words
