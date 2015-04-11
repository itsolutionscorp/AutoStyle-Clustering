def distance(str_first, str_second):
    dist = 0
    for i in range(0, len(str_first)):
        if str_first[i] != str_second[i]:
            dist = dist + 1

    return dist
