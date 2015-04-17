def word_count(line):
    d = {}
    for word in line.split():
        if word in d:
            d[word] += 1
        else:
            d[word] = 1
    return d
