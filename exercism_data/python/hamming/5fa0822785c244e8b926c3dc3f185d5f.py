def distance(progenitor, progeny):
    d = 0
    for i in range(len(progenitor)):
        if progenitor[i] != progeny[i]:
            d += 1
    return d
