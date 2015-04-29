def word_count(input):
    count = {}
    for word in input.split():
        if word not in count:
            count[word] = 0
        count[word] += 1
    return count
