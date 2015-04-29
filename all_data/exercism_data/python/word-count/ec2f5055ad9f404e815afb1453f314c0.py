def word_count(what):
    count = {}
    for word in what.split():
        if not count.has_key(word):
            count[word] = 0
        count[word] += 1

    return count
