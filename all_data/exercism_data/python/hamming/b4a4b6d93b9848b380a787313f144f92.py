def distance(stringA, stringB):
    count = 0
    for x in enumerate(stringA):
        if x[1] != stringB[x[0]]:
            count += 1

    return count
