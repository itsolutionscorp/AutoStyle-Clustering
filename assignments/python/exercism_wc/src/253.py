def word_count(input):
    count = {}
    for word in input.split():
        if word in count:
            count[word] = count[word] + 1
        else:
            count[word] = 1
    return count
