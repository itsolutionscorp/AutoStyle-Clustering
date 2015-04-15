def word_count(string):
    dictionaire = {}
    string = string.split()
    for i in string:
        if not i in dictionaire:
            dictionaire[i] = 1
        else:
            dictionaire[i] +=1

    return dictionaire
