def word_count(what):
    count = {}
    for word in what.split():
        if word in count:
            count[word] += 1
        else:
            count[word] = 1
    return count
