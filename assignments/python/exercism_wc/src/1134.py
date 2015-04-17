def word_count(text):
    words = text.split()
    count = {}
    for w in words:
        if w in count:
            count[w] += 1
        else:
            count[w] = 1
    return count
