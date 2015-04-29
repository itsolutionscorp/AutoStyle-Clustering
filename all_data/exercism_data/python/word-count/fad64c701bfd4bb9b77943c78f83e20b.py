def word_count(string):
    freq = {}
    for word in string.split():
        freq.setdefault(word, 0)
        freq[word] += 1
    return freq
