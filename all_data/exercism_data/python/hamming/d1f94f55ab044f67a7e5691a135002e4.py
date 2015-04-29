def distance(original, mutated):
    h_d = 0
    for position, nbase in enumerate(original):
        if mutated[position] != nbase:
            h_d += 1
    return h_d
