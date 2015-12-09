def num_common_letters(first, second):
    lst = []
    total = 0
    for a in first:
        if a not in lst:
            lst += a
    for b in second:
        if b in lst:
            total += 1
            lst.remove(b)
    return total
