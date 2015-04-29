def word_count(input):
    list = input.split()
    uniques = set(list)
    occurances = {}
    for i in uniques:
        occurances[i] = (list.count(i))
    return occurances
