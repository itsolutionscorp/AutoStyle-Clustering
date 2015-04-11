def word_count(what):
    count = {}
    for word in what.split():
        if not word in count:
            count[word] = 0
        count[word] += 1

    return count
