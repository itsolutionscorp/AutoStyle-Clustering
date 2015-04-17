def word_count(phrase):
    count_dict = {}
    words = phrase.split()
    for word in words:
        if word in count_dict.keys():
            count_dict[word] += 1
        else:
            count_dict[word] = 1
    return count_dict
