stack = []
def word_count(line):
    stack = line.split()
    orged = {}
    for item in stack:
        if item in orged:
            orged[item] += 1
        else:
            orged[item] = 1
    return orged
