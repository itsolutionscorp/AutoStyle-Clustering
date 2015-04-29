def word_count(text):
    words = text.split()
    count_dictionary = {}
    for word in words:
        if word not in count_dictionary:
            count_dictionary[word] = 1
        else:
            count_dictionary[word] += 1
    return count_dictionary
