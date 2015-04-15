def distance(strand1, strand2):
    value = 0
    part1 = list(strand1)
    part2 = list(strand2)
    for i in range(0, len(part1)):
        if part1[i] != part2[i]:
            value += 1
    return value
