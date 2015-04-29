def distance(first,second):
    count = 0
    for i in range(len(first)):
        if first[i] != second[i]:
            count += 1
    return count
