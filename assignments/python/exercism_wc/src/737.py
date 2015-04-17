def word_count(string):
    count = dict()
    string = string.strip()
    for word in string.split():
        if word in count:
            count[word] += 1
        if word not in count:
            count[word] = 1
    return count
