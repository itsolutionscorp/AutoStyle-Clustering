def word_count(text):

    words = text.split()
    count = {}

    #count words
    for w in words:
        if w in count:
            count[w] += 1
        else:
            count[w] = 1

    #output dictionary
    return count
