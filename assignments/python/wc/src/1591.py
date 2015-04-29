def word_count(string):
    string = string.split()
    dico = {}
    for word in string:
        if word not in dico:
            dico[word] = 1
        else:
            dico[word] += 1
    return dico
