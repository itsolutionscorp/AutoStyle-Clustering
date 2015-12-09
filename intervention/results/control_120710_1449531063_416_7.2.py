def num_common_letters2(first, second):
    total = 0
    for a in set(first):
        if a in set(second):
            total += 1
    return total