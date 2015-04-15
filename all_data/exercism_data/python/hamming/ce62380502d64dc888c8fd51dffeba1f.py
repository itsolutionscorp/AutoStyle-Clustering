def distance(first, second):
    first_list = list(first)
    second_list = list(second)

    dist = 0
    for i in range(0, len(first_list)):
        if first_list[i] != second_list[i]:
            dist = dist + 1
            
    return dist
