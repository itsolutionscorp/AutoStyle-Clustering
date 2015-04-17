def word_count(string):
    string = string.split()
    word_list = list(set(string))
    result = {}
    for i in word_list:
        result[i] = string.count(i)
    return result
