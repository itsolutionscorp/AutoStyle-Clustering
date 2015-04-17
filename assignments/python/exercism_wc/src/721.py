def word_count(line):
    words = dict()
    for word in line.split():
        if word in words:
            words[word] += 1
        else:
            words[word] = 1
    return words
