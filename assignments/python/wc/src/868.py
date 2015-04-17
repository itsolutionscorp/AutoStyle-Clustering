def word_count(data):
    dictionary = {}
    for word in data.split():
        if word not in dictionary:
            dictionary[word] = 1
        else:
            dictionary[word] += 1
    return dictionary
