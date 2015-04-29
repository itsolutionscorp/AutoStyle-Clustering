def word_count(words):
    count = dict()
    for word in words.split():
        if word in count:
            count[word] += 1
        else:
            count[word] = 1
    return count
