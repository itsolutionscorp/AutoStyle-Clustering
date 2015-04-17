def word_count(words):
    array = words.split()
    dictionary = {}
    for word in array:
        if word in dictionary:
            dictionary[word] += 1
        else:
            dictionary[word] = 1
    return dictionary
