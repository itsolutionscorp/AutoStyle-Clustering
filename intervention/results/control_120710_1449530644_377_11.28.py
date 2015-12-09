def num_common_letters(first, second):
    lst = []
    total = 0
    for a in first:
        if a not in lst:
            lst += a
            if a in second:
                total += 1
    return total