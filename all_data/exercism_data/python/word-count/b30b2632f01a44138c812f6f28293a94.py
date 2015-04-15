def word_count(string):
    
    count_dict = {}
    for word in string.split():
        if word in count_dict:
            count_dict[word] += 1
        else:
            count_dict[word] = 1

    return count_dict
