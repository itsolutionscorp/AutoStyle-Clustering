def word_count(string):
    words = {}
    string = string.replace('\n',' ').split(' ')
    for each in string:
        if len(each):
            try:
                words[each] += 1
            except KeyError:
                words[each] = 1
    return words
